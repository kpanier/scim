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

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.aysada.scim.service.db.entity.ProjectEntity;
import org.aysada.scim.service.db.manager.ProjectManager;
import org.aysada.scim.service.rest.mapper.ProjectMapper;
import org.aysada.scim.service.rest.model.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectEndPoint implements ProjectService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectEndPoint.class);

	@Inject
	private ProjectManager projectManager;

	@Inject
	private ProjectMapper projectMapper;

	@Override
	public Response getProjects() {
		List<ProjectEntity> allProjects = projectManager.getAllProjects();
		List<Project> result = projectMapper.map(allProjects);
		return Response.ok(result).build();
	}

	@Override
	public Response getProject(String id) {
		ProjectEntity projectEntity = projectManager.getProjectEntityWithId(id);
		Project project = projectMapper.map(projectEntity);
		LOGGER.info("Found project: {} with name: {} in db.", project, id);
		return Response.ok(project).build();
	}

	@Override
	public Response receive(Project project) {
		ProjectEntity entity = projectMapper.mapToEntity(project);
		if (project.getId() == null) {
			projectManager.add(entity);
			LOGGER.info("Project: {} added to db.", project);
		} else {
			projectManager.update(entity);
			LOGGER.info("Project: {} updated in db.", project);
		}
		return Response.ok().build();
	}

}
