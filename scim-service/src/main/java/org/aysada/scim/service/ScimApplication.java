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


import javax.enterprise.inject.Produces;

import org.aysada.scim.service.rest.ProjectEndPoint;
import org.jboss.weld.environment.servlet.Listener;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.meltmedia.dropwizard.mongo.MongoBundle;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.swagger.jaxrs.listing.ApiListingResource;

public class ScimApplication extends Application<ScimConfiguration> {

	private static MongoBundle<ScimConfiguration> mongoBundle = MongoBundle
			.<ScimConfiguration>builder().withConfiguration(ScimConfiguration::getMongo)
			.build();

	public static void main(final String[] args) throws Exception {
		new ScimApplication().run(args);
	}

	@Override
	public void initialize(Bootstrap<ScimConfiguration> bootstrap) {
		bootstrap.addBundle(mongoBundle);
	}

	@Override
	public void run(ScimConfiguration configuration, Environment environment) throws Exception {
		// Swagger documentation servlet
		environment.jersey().register(new ApiListingResource());
		environment.jersey().register(ProjectEndPoint.class);
		environment.jersey().register(CORSFilter.class);
		environment.getObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

		// Enable WELD support
		environment.servlets().addServletListeners(new Listener());

	}

	public static class MongoDatastoreProducer {
		@Produces
		public static Datastore produceMorphia() {
			if (mongoBundle.getDB() != null) {
				String dbName = mongoBundle.getDB().getName();
				final Datastore datastore = new Morphia().createDatastore(mongoBundle.getClient(), dbName);
				datastore.ensureIndexes();
				return datastore;
			}
			return null;
		}
	}

}
