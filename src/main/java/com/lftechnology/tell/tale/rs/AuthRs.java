package com.lftechnology.tell.tale.rs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 
 * @author Achyut Pokhrel <achyutpokhrel@lftechnology.com>
 *
 */
@Path("login")
public class AuthRs {
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public String login(){
		return "hello";
	}
}
