package com.lftechnology.tell.tale.rs;

import java.util.Map;
import java.util.UUID;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
	
	@Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(@DefaultValue("1") @QueryParam("start") String start, @DefaultValue("10") @QueryParam("offset") String offset) {
        Map<String, Object> suggestions = this.suggestionService.find(start, offset);
        return Response.status(Response.Status.OK).entity(suggestions).build();
    }
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed("Admin")
	public Response create(Suggestion suggestion){
		return Response.status(Response.Status.OK).entity(suggestionService.save(suggestion)).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getSingleSuggestion(@PathParam("id") String id){
		UUID uid = UUID.fromString(id);
		return Response.status(Response.Status.OK).entity(suggestionService.findOne(uid)).build();
	}
	
}
