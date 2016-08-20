package cn.swu.edu.opensource.openswu_webapi_jersey.auth;

import cn.swu.edu.opensource.openswu_webapi_jersey.utils.ConnDb;
import org.glassfish.jersey.server.ContainerRequest;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by 西南大学开源协会 陈思定  on 2016/8/17.
 * <p>
 * Email : sidingchan@gmail.com
 */

public class AuthFilter implements ContainerRequestFilter {


    final String tokenQueryString =
            "select * " +
                    "from developer " +
                    "where username= admin and token = ?";

    @Override
    public void filter(ContainerRequestContext containerRequestContext) {
        String token = containerRequestContext.getHeaderString("Authorization");

        if (token == null || token.isEmpty()) {
            abortFilterChain(containerRequestContext, "User cannot access the resource.");
        }

        try (Connection con = new ConnDb().getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(tokenQueryString);
            pstmt.setString(1, token);

            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                abortFilterChain(containerRequestContext, "User cannot access the resource.");
            }

        } catch (SQLException e) {
            abortFilterChain(containerRequestContext, "Database problem");
        } catch (IOException e) {
            abortFilterChain(containerRequestContext, "Server problem");
        }


    }

    private void abortFilterChain(ContainerRequestContext crc, String response) {
        crc.abortWith(Response
                .status(Response.Status.UNAUTHORIZED)
                .entity(response)
                .build());
    }
}
