package com.lftechnology.tell.tale.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.lftechnology.tell.tale.pojo.ErrorMessage;

/**
 * @author Achyut Pokhrel <achyutpokhrel@lftechnology.com>
 * Jun 24, 2016
 * 
 */
@Provider
public class EncryptionExeptionMapper implements ExceptionMapper<EncryptionException>{

	@Override
	public Response toResponse(EncryptionException exception) {
		ErrorMessage errorMessage = new ErrorMessage(exception.getMessage());
        return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(errorMessage)
                .type(MediaType.APPLICATION_JSON).build();
	}

}
