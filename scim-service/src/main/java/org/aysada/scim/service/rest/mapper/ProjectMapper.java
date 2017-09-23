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

import java.util.List;

import javax.annotation.PostConstruct;

import org.aysada.scim.service.db.entity.ProjectEntity;
import org.aysada.scim.service.rest.model.Project;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class ProjectMapper {

	private MapperFacade mapper;

	@PostConstruct
	public void init() {
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder()
				.mappingContextFactory(new MappingContext.Factory()).mapNulls(false).build();
		mapper = mapperFactory.getMapperFacade();
	}

	public List<Project> map(List<ProjectEntity> projects) {
		return mapper.mapAsList(projects, Project.class);
	}

	public ProjectEntity mapToEntity(Project project) {
		return mapper.map(project, ProjectEntity.class);
	}

	public Project map(ProjectEntity projectEntity) {
		return mapper.map(projectEntity, Project.class);
	}

}
