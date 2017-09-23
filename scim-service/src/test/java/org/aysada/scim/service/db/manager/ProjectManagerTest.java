/*******************************************************************************
 * Copyright (c) 2017 Karsten Panier and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Karsten Panier - initial API and implementation
 *******************************************************************************/
package org.aysada.scim.service.db.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.aysada.scim.service.db.entity.ProjectEntity;
import org.aysada.scim.service.testutil.AssertProject;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import com.github.fakemongo.Fongo;
import com.mongodb.DBCollection;

public class ProjectManagerTest {

	private ProjectManager projectManager;
	private Datastore datastore;

	@Before
	public void setup() {
		Fongo fongo = new Fongo("test");
		datastore = new Morphia().createDatastore(fongo.getMongo(), "testdb");
		projectManager = new ProjectManager(datastore);
	}

	@Test
	public void readAllProjects() throws Exception {
		// given
		List<ProjectEntity> list = Arrays.asList(new ProjectEntity[] { new ProjectEntity("Project A", "http://github"),
				new ProjectEntity("Project B", "http://gitlab") });
		datastore.save(list);

		// when
		List<ProjectEntity> projects = projectManager.getAllProjects();

		// then
		assertNotNull(projects);
		assertEquals(2, projects.size());
		ProjectEntity prj1 = projects.get(0);
		assertEquals("Project A", prj1.getName());
	}

	@Test
	public void testAddProject() throws Exception {
		// given
		String name = "foo";
		String url = "ftp://foo";
		ProjectEntity entity = new ProjectEntity(name, url);

		// when
		projectManager.add(entity);

		// then
		assertNotNull(entity.getId());
		DBCollection collection = datastore.getDB().getCollection("projects");
		assertEquals(1, collection.count());
		Query<ProjectEntity> query = datastore.createQuery(ProjectEntity.class);
		query.filter("name =", name);
		ProjectEntity projectEntity = query.get();
		AssertProject.assertProjectEntity(name, url, projectEntity);
	}

	@Test
	public void testUpdateProject() throws Exception {
		// given
		ProjectEntity entity = new ProjectEntity();
		projectManager.add(entity);
		entity.setName("bar");

		// when
		projectManager.update(entity);

		// then
		Query<ProjectEntity> query = datastore.createQuery(ProjectEntity.class);
		query.filter("id =", entity.getId());
		ProjectEntity projectEntity = query.get();
		assertEquals("bar", projectEntity.getName());
	}

	@Test
	public void testUpdateProjectFailsOnNullId() throws Exception {
		// given
		ProjectEntity entity = new ProjectEntity();

		try {
			// when
			projectManager.update(entity);
			// then
			fail("Entity without id should lead to an exception.");
		} catch (RuntimeException e) {
			assertEquals("Update operation only possible on exisiting object. The current has no id.", e.getMessage());
		}
	}

	@Test
	public void testUpdateProjectFailsOnNotExistingEntity() throws Exception {
		// given
		ProjectEntity entity = new ProjectEntity();
		entity.setId("foo");

		try {
			// when
			projectManager.update(entity);
			// then
			fail("Entity thats not in the db should lead to an exception.");
		} catch (RuntimeException e) {
			assertEquals("Update operation only possible on exisiting object. The current object was not found.", e.getMessage());
		}
	}

	@Test
	public void testGetProjectEntityWithId() throws Exception {
		// given
		ProjectEntity entity = new ProjectEntity("Hello", "foo");
		entity.setId("foo");
		datastore.save(entity);

		// when
		ProjectEntity projectEntity = projectManager.getProjectEntityWithId("foo");

		// then
		AssertProject.assertProjectEntity("Hello", "foo", projectEntity);
	}

	@Test
	public void testGetProjectEntityWithName() throws Exception {
		// given
		String name = "myPrj";
		datastore.save(new ProjectEntity(name, "foo"));

		// when
		ProjectEntity projectEntity = projectManager.getProjectEntityWithName(name);

		// then
		AssertProject.assertProjectEntity(name, "foo", projectEntity);
	}

}
