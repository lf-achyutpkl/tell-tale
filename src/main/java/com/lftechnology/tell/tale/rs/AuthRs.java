package com.lftechnology.tell.tale.rs;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.lftechnology.tell.tale.dao.UserDao;
import com.lftechnology.tell.tale.entity.User;

/**
 * 
 * @author Achyut Pokhrel <achyutpokhrel@lftechnology.com>
 *
 */
@Path("login")
public class AuthRs {

    @Inject
    UserDao userDao;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public User login() {
        User user = new User();
        user.setEmail("prajjwal@admin.com");
        return user;
    }

}
