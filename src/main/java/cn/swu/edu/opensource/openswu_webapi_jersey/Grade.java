package cn.swu.edu.opensource.openswu_webapi_jersey;

import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import org.glassfish.jersey.server.ContainerRequest;

/**
 * 
 * @author 陈思定
 *
 */

@Path("lookupgrade")
public class Grade {
	@Context
	ContainerRequest containerRequest;
	
	
}
