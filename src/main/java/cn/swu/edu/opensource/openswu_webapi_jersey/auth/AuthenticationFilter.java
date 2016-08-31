package cn.swu.edu.opensource.openswu_webapi_jersey.auth;

import cn.swu.edu.opensource.openswu_webapi_jersey.exception.AuthenticationException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

/**
 * Created by 西南大学开源协会 陈思定  on 2016/8/17.
 * <p>
 * Email : sidingchan@gmail.com
 */

@Provider
public class AuthenticationFilter implements ContainerRequestFilter {

    // 暂时将认证直接放在代码中
    private static final String authName = "opensource";
    private static final String authPassword = "freedom";

    @Override
    public void filter(ContainerRequestContext containerRequestContext) {
        String authentication = containerRequestContext.getHeaderString("Authorization");

        if (authentication == null || authentication.isEmpty()) {
            throw new AuthenticationException("Authentication credentials are required", null);
//            abortFilterChain(containerRequestContext, "User cannot access the resource.");
        } else {
            if (!authentication.startsWith("Basic ")) {
                throw new AuthenticationException("Authentication credentials are required", null);
                // additional checks should be done here
                // "Only HTTP Basic authentication is supported"
            }
            authentication = authentication.substring("Basic ".length());
            String[] values = new String(Base64.base64Decode(authentication)).split(":");
            if (values.length < 2) {
                throw new AuthenticationException("Invalid syntax for username and password", null);
                // additional checks should be done here
                // "Invalid syntax for username and password"
            }
            String username = values[0];
            String password = values[1];
            if ((username == null) || (password == null)) {
                throw new AuthenticationException("Missing username or password", null);
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
                throw new AuthenticationException("Invalid username or password\r\n", null);
            }
        }


    }

}
