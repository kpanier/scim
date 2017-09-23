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
package org.aysada.scim.service.rest.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.aysada.scim.service.db.entity.ProjectEntity;
import org.aysada.scim.service.rest.model.Project;
import org.junit.Before;
import org.junit.Test;

public class ProjectMapperTest {

	private ProjectMapper mapper;
	private String name = "myProject";
	private String repoUrl = "https://foo";

	@Before
	public void setup() {
		mapper = new ProjectMapper();
		mapper.init();
	}

	@Test
	public void testMapEntityListToRest() throws Exception {
		// given
		List<ProjectEntity> projects = Arrays.asList(new ProjectEntity[] { new ProjectEntity(name, repoUrl) });

		// when
		List<Project> list = mapper.map(projects);

		// then
		assertNotNull(list);
		Project project = list.get(0);
		assertEquals(name, project.getName());
		assertEquals(repoUrl, project.getGitUrl());
	}

	@Test
	public void testMapProjectToEntity() throws Exception {
		// given
		Project project = new Project();
		project.setName(name);
		project.setGitUrl(repoUrl);

		// when
		ProjectEntity projectEntity = mapper.mapToEntity(project);

		// then
		assertEquals(name, projectEntity.getName());
		assertEquals(repoUrl, projectEntity.getGitUrl());
	}

	@Test
	public void testMapProjectEntityToProject() throws Exception {
		// given
		ProjectEntity projectEntity = new ProjectEntity(name, repoUrl);

		// when
		Project project = mapper.map(projectEntity);

		// then
		assertEquals(name, project.getName());
		assertEquals(repoUrl, project.getGitUrl());
	}

}
