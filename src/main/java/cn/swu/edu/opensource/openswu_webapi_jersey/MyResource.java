package cn.swu.edu.opensource.openswu_webapi_jersey;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.glassfish.grizzly.http.HttpHeader;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.HttpCodecFilter.HeaderParsingState;
import org.glassfish.grizzly.http.util.Header;
import org.glassfish.jersey.server.ContainerRequest;

import cn.swu.edu.opensource.openswu_webapi_jersey.auth.SecurityFilter;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
	@Context 
	ContainerRequest cr;
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
    	SecurityFilter filter = new SecurityFilter();
    	
    
        return "Got it!";
    }
}
