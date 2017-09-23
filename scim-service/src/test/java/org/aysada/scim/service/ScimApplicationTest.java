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
package org.aysada.scim.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import org.jboss.weld.environment.servlet.Listener;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import io.dropwizard.setup.Environment;
import io.swagger.jaxrs.listing.ApiListingResource;

public class ScimApplicationTest {

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Mock(answer = Answers.RETURNS_DEEP_STUBS)
	private Environment env;

	private ScimApplication application = new ScimApplication();

	@Test
	public void testRunWithCorrectServerConfig() throws Exception {
		// given

		// when
		application.run(new ScimConfiguration(), env);

		// then
		// swagger docu service
		verify(env.jersey()).register(any(ApiListingResource.class));
		// weld CDI
		verify(env.servlets()).addServletListeners(any(Listener.class));
	}
	
}
