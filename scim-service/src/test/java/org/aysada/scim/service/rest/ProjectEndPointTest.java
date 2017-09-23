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
package org.aysada.scim.service.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.aysada.scim.service.db.entity.ProjectEntity;
import org.aysada.scim.service.db.manager.ProjectManager;
import org.aysada.scim.service.rest.mapper.ProjectMapper;
import org.aysada.scim.service.rest.model.Project;
import org.aysada.scim.service.testutil.AssertProject;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

@RunWith(CdiRunner.class)
@AdditionalClasses({ ProjectEndPoint.class, ProjectMapper.class })
public class ProjectEndPointTest {

	@Produces
	@Mock
	private ProjectManager projectManager;

	@Inject
	private ProjectService endPoint;

	@Captor
	private ArgumentCaptor<ProjectEntity> peCaptor;

	String name = "myPrj";
	String gitUrl = "url";

	private Project project;

	@Before
	public void setupTestData() {
		project = new Project();
		project.setName(name);
		project.setGitUrl(gitUrl);

	}

	@Test
	public void getAllProjects() throws Exception {
		// given
		when(projectManager.getAllProjects())
				.thenReturn(Arrays.asList(new ProjectEntity[] { new ProjectEntity(name, gitUrl) }));

		// when
		List<Project> result = (List<Project>) endPoint.getProjects().getEntity();

		// then
		assertNotNull(result);
		Project project = result.get(0);
		AssertProject.assertProject(name, gitUrl, project);
	}

	@Test
	public void testGetSignleProject() throws Exception {
		// given
		when(projectManager.getProjectEntityWithId(name)).thenReturn(new ProjectEntity(name, gitUrl));

		// when
		Project entity = (Project) endPoint.getProject(name).getEntity();

		// then
		AssertProject.assertProject(name, gitUrl, entity);
	}

	@Test
	public void testReciveNewProject() throws Exception {
		// given see setupTestDate

		// when
		endPoint.receive(project);

		// then
		verify(projectManager).add(peCaptor.capture());
		AssertProject.assertProjectEntity(name, gitUrl, peCaptor.getValue());
	}

	@Test
	public void testReciveProjectUPdate() throws Exception {
		// given
		project.setId("Foo");

		// when
		endPoint.receive(project);

		// then
		verify(projectManager).update(peCaptor.capture());
		AssertProject.assertProjectEntity(name, gitUrl, peCaptor.getValue());
		assertEquals("Foo", peCaptor.getValue().getId());
	}

}
