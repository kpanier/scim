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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meltmedia.dropwizard.mongo.MongoConfiguration;

import io.dropwizard.Configuration;

public class ScimConfiguration extends Configuration {

	@JsonProperty
	private MongoConfiguration mongo = new MongoConfiguration();

	public MongoConfiguration getMongo() {
		return mongo;
	}

	public void setMongo(MongoConfiguration mongo) {
		this.mongo = mongo;
	}

}
