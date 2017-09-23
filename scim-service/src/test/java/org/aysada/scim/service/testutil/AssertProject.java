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
package org.aysada.scim.service.testutil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.aysada.scim.service.db.entity.ProjectEntity;
import org.aysada.scim.service.rest.model.Project;

public class AssertProject {

	public static void assertProject(String name, String url, Project project) {
		assertNotNull(project);
		assertEquals(name, project.getName());
		assertEquals(url, project.getGitUrl());
	}

	public static void assertProjectEntity(String name, String url, ProjectEntity projectEntity) {
		assertNotNull(projectEntity);
		assertEquals(name, projectEntity.getName());
		assertEquals(url, projectEntity.getGitUrl());
	}

}
