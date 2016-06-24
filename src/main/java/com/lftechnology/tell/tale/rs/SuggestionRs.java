package com.lftechnology.tell.tale.rs;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.lftechnology.tell.tale.entity.Suggestion;
import com.lftechnology.tell.tale.service.SuggestionService;

/**
 * @author Achyut Pokhrel <achyutpokhrel@lftechnology.com>
 * Jun 25, 2016
 * 
 */
@Path("/suggestions")
public class SuggestionRs {

	@Inject
	private SuggestionService suggestionService;
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed("Admin")
	public Response list(){
		return Response.status(Response.Status.OK).entity("la thik cha").build();
	}
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed("Admin")
	public Response create(@Valid Suggestion suggestion){
		suggestionService.save(suggestion);
		return Response.status(Response.Status.OK).build();
	}
	
}
