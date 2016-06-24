package com.lftechnology.tell.tale.httpfilter;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import com.lftechnology.tell.tale.entity.User;
import com.lftechnology.tell.tale.pojo.SecurityRequestContext;
import com.lftechnology.tell.tale.service.JwtTokenService;

/**
 * @author Achyut Pokhrel <achyutpokhrel@lftechnology.com> Jun 25, 2016
 * 
 */
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

	@Inject
	private JwtTokenService jwtServiceToken;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

			final String token = authorizationHeader.substring("Bearer".length()).trim();
			Map<String, Object> payload = jwtServiceToken.validate(token);

			setSecurityRequestContext(token);

			requestContext.setSecurityContext(new SecurityContext() {
				@Override
				public boolean isUserInRole(String role) {
					return true;
				}

				@Override
				public boolean isSecure() {
					return false;
				}

				@Override
				public Principal getUserPrincipal() {
					return new Principal() {

						@Override
						public String getName() {
							return token;
						}
					};
				}

				@Override
				public String getAuthenticationScheme() {
					return null;
				}
			});
		}

	}

	private void setSecurityRequestContext(final String token) {
		String[] payloads = jwtServiceToken.decodePayLoad(token);
		User user = new User();
		user.setId(UUID.fromString(payloads[1]));
		SecurityRequestContext.setRandomKey(payloads[0]);
		SecurityRequestContext.setCurrentUser(user);
	}

}
