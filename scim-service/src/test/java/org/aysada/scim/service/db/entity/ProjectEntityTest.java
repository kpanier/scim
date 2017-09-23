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
package org.aysada.scim.service.db.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ProjectEntityTest {

	@Test
	public void verifyConstructor() throws Exception {
		// given
		String name = "name";
		String repoUrl = "http://repo";

		// when
		ProjectEntity projectEntity = new ProjectEntity(name, repoUrl);

		// then
		assertEquals(name, projectEntity.getName());
		assertEquals(repoUrl, projectEntity.getGitUrl());
	}

}
