package com.lftechnology.tell.tale.rs;

import java.util.Map;
import java.util.UUID;

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

import com.lftechnology.tell.tale.dao.UserDao;
import com.lftechnology.tell.tale.entity.User;
import com.lftechnology.tell.tale.exception.ObjectNotFoundException;
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

}
