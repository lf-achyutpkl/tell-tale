package com.lftechnology.tell.tale.rs;

import java.util.List;
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

import com.lftechnology.tell.tale.entity.User;
import com.lftechnology.tell.tale.exception.ObjectNotFoundException;
import com.lftechnology.tell.tale.exception.UnauthorizedException;
import com.lftechnology.tell.tale.pojo.SecurityRequestContext;
import com.lftechnology.tell.tale.service.UserService;

/**
 * 
 * @author Prajjwal Raj Kandel<prajjwalkandel@lftechnology.com>
 *
 */
@Path("users")
public class UserRs {

    @Inject
    private UserService userService;

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid User user) {
        return Response.status(Response.Status.OK).entity(this.userService.save(user)).build();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User findById(@PathParam("id") UUID id) {
        User user = this.userService.findOne(id);
        if (user != null) {
            return user;
        } else {
            throw new ObjectNotFoundException();
        }
    }

    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(@DefaultValue("1") @QueryParam("start") String start, @DefaultValue("10") @QueryParam("offset") String offset) {
        Map<String, Object> users = this.userService.find(start, offset);
        return Response.status(Response.Status.OK).entity(users).build();
    }
    
    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(User user) {
        return Response.status(Response.Status.OK).entity(this.userService.login(user)).build();
    }
    
    @POST
    @Path("logout")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("Admin")
    public Response logout(){
    	User user = SecurityRequestContext.getCurrentUser();
    	if(user == null){
    		throw new UnauthorizedException();
    	}
    	userService.logout(user);
    	return Response.status(Response.Status.OK).build();
    }
    
    @Path("/search")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response search(@DefaultValue("name") @QueryParam("q") String search) {
        List<User> users = this.userService.search(search);
        return Response.status(Response.Status.OK).entity(users).build();
    }

}
