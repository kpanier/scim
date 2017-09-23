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

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.aysada.scim.service.db.entity.ProjectEntity;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectManager.class);

	private Datastore datastore;

	@Inject
	public ProjectManager(Datastore datastore) {
		this.datastore = datastore;
	}

	public List<ProjectEntity> getAllProjects() {
		return datastore.createQuery(ProjectEntity.class).asList();
	}

	public void update(ProjectEntity entity) {
		if (entity.getId() == null) {
			throw new RuntimeException("Update operation only possible on exisiting object. The current has no id.");
		}
		if (getProjectEntityWithId(entity.getId()) == null) {
			throw new RuntimeException(
					"Update operation only possible on exisiting object. The current object was not found.");
		}
		datastore.save(entity);
	}

	public void add(ProjectEntity entity) {
		entity.setId(UUID.randomUUID().toString());
		datastore.save(entity);
	}

	public ProjectEntity getProjectEntityWithName(String name) {
		Query<ProjectEntity> query = datastore.createQuery(ProjectEntity.class);
		query.filter("name ", name);
		ProjectEntity result = query.get();
		LOGGER.info("Found entity: {} with name: {} in db.", result, name);
		return result;
	}

	public ProjectEntity getProjectEntityWithId(String id) {
		Query<ProjectEntity> query = datastore.createQuery(ProjectEntity.class);
		query.filter("id ", id);
		ProjectEntity result = query.get();
		LOGGER.info("Found entity: {} with id: {} in db.", result, id);
		return result;
	}

}
