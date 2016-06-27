package com.lftechnology.tell.tale.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.lftechnology.tell.tale.pojo.ErrorMessage;

/**
 * 
 * @author Prajjwal Raj Kandel<prajjwalkandel@lftechnology.com>
 *
 */

@Provider
public class UnauthorizedExceptionMapper implements ExceptionMapper<UnauthorizedException> {

    @Override
    public Response toResponse(UnauthorizedException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage());
        return Response.status(Response.Status.UNAUTHORIZED).entity(errorMessage).type(MediaType.APPLICATION_JSON).build();
    }
}