package cn.swu.edu.opensource.openswu_webapi_jersey;

import cn.swu.edu.opensource.openswu_webapi_jersey.auth.SecurityFilter;
import org.glassfish.jersey.server.ContainerRequest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

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
