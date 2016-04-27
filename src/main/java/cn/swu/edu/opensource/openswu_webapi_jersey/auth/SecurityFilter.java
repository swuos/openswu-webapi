/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.swu.edu.opensource.openswu_webapi_jersey.auth;

import org.glassfish.jersey.server.ContainerRequest;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;

/**
 *
 * Simplier version of Security filter from Atom example
 */
public class SecurityFilter implements ContainerRequestFilter {

	private static final String REALM = "HTTPS Example authentization";
	private final String authName = "opensource";
	private final String authPassword = "freedom";
	@Context
	UriInfo uriInfo;

	// public ContainerRequest filter(ContainerRequest request) {
	// User user = authenticate(request);
	// request.setSecurityContext(new Authorizer(user));
	// return request;
	// }

	// private User authenticate(ContainerRequest request) {
	// // Extract authentication credentials
	// String authentication =
	// request.getHeaderString(ContainerRequest.AUTHORIZATION);
	// if (authentication == null) {
	// throw new AuthenticationException("Authentication credentials are
	// required", REALM);
	// }
	// if (!authentication.startsWith("Basic ")) {
	// return null;
	// // additional checks should be done here
	// // "Only HTTP Basic authentication is supported"
	// }
	// authentication = authentication.substring("Basic ".length());
	// String[] values = new
	// String(Base64.base64Decode(authentication)).split(":");
	// if (values.length < 2) {
	// return null;
	// // additional checks should be done here
	// // "Invalid syntax for username and password"
	// }
	// String username = values[0];
	// String password = values[1];
	// if ((username == null) || (password == null)) {
	// return null;
	// // additional checks should be done here
	// // "Missing username or password"
	// }
	//
	// // Validate the extracted credentials
	// User user = null;
	//
	// // BUG IN HTTPBasicAuthFilter? (Added \0\0 at the end of password) TODO
	// XXX FIXME
	//
	// // System.out.println("--->" + username + ":" + password + "<");
	// // System.out.println(username.equals("user"));
	// // System.out.println(password.equals("password\0\0"));
	// //
	// // for(char c : password.toCharArray()) {
	// // System.out.println("->" + (int) c);
	// // }
	//
	// if (username.equals(authName) && password.trim().equals(authPassword)) {
	// user = new User("user", "user");
	// System.out.println("USER AUTHENTICATED");
	// // } else if (username.equals("admin") && password.equals("adminadmin"))
	// {
	// // user = new User("admin", "admin");
	// // System.out.println("ADMIN AUTHENTICATED");
	// } else {
	// System.out.println("USER NOT AUTHENTICATED");
	// throw new AuthenticationException("Invalid username or password\r\n",
	// REALM);
	// }
	// return user;
	// }

	// public class Authorizer implements SecurityContext {
	//
	// private User user;
	// private Principal principal;
	//
	// public Authorizer(final User user) {
	// this.user = user;
	// this.principal = new Principal() {
	//
	// public String getName() {
	// return user.username;
	// }
	// };
	// }
	//
	// public Principal getUserPrincipal() {
	// return this.principal;
	// }
	//
	// public boolean isUserInRole(String role) {
	// return (role.equals(user.role));
	// }
	//
	// public boolean isSecure() {
	// return "https".equals(uriInfo.getRequestUri().getScheme());
	// }
	//
	// public String getAuthenticationScheme() {
	// return SecurityContext.BASIC_AUTH;
	// }
	// }
	//
	// public class User {
	//
	// public String username;
	// public String role;
	//
	// public User(String username, String role) {
	// this.username = username;
	// this.role = role;
	// }
	// }

	@Override
	public void filter(ContainerRequestContext arg0) throws IOException {
		// TODO Auto-generated method stub

	}

	public ContainerRequest filter(ContainerRequest request) {
		String authentication = request.getHeaderString(ContainerRequest.AUTHORIZATION);
		if (authentication == null) {
			throw new AuthenticationException("Authentication credentials are required", REALM);
		}
		if (!authentication.startsWith("Basic ")) {
			return null;
			// additional checks should be done here
			// "Only HTTP Basic authentication is supported"
		}
		authentication = authentication.substring("Basic ".length());
		String[] values = new String(Base64.base64Decode(authentication)).split(":");
		if (values.length < 2) {
			return null;
			// additional checks should be done here
			// "Invalid syntax for username and password"
		}
		String username = values[0];
		String password = values[1];
		if ((username == null) || (password == null)) {
			return null;
			// additional checks should be done here
			// "Missing username or password"
		}

		// Validate the extracted credentials
		// User user = null;

		// BUG IN HTTPBasicAuthFilter? (Added \0\0 at the end of password) TODO
		// XXX FIXME

		// System.out.println("--->" + username + ":" + password + "<");
		// System.out.println(username.equals("user"));
		// System.out.println(password.equals("password\0\0"));
		//
		// for(char c : password.toCharArray()) {
		// System.out.println("->" + (int) c);
		// }

		if (username.equals(authName) && password.trim().equals(authPassword)) {
			// user = new User("user", "user");
			System.out.println("USER AUTHENTICATED");
			// } else if (username.equals("admin") &&
			// password.equals("adminadmin")) {
			// user = new User("admin", "admin");
			// System.out.println("ADMIN AUTHENTICATED");
		} else {
			System.out.println("USER NOT AUTHENTICATED");
			throw new AuthenticationException("Invalid username or password\r\n", REALM);
		}
		return null;
	}
}
