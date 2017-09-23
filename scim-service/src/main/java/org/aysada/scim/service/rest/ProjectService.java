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

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.aysada.scim.service.rest.model.Project;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api
@Path("projects")
public interface ProjectService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get all projects", notes = "get a list of all projects.", response = Project.class, responseContainer = "List")
	public Response getProjects();

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get Project identified by name", response = Project.class)
	public Response getProject(@ApiParam(value = "name of the project.", required = true) @PathParam("id") String id);

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Creates or updates a project in the datastore.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Project has been updated or created."),
			@ApiResponse(code = 500, message = "Error storing Project.") })
	public Response receive(@ApiParam(value = "A solution") @Valid @NotNull Project project);

}
