package cn.swu.edu.opensource.openswu_webapi_jersey.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by 西南大学开源协会 陈思定  on 2016/8/30.
 * <p>
 * Email : sidingchan@gmail.com
 * <p>
 * <p>
 * <p>Map an authentication exceptions to an HTTP 401 response, optionally
 * including the realm for a credentials challenge at the client.</p>
 */

@Provider
public class ParameterExceptionMapper implements ExceptionMapper<ParameterException> {


    @Override
    public Response toResponse(ParameterException e) {

        if (e.getRealm() == null) {
            return Response.
                    status(Response.Status.UNAUTHORIZED).
                    header("WWW-Authenticate", "Basic realm=\"" + e.getRealm() + "\"").
                    type("text/plain").
                    entity(e.getMessage()).
                    build();

        } else {
            return Response.
                    status(Response.Status.UNAUTHORIZED).
                    type("text/plain").
                    entity(e.getMessage()).
                    build();
        }

    }
}
