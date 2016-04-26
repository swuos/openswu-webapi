package cn.swu.edu.opensource.openswu_webapi_jersey;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import cn.swu.edu.opensource.openswu_webapi_jersey.grade.SearchParam;
import cn.swu.edu.opensource.openswu_webapi_jersey.grade.SwuGrades;
import cn.swu.edu.opensource.openswu_webapi_jersey.login.Login;

/**	
 * 
 * @author 陈思定
 *
 */

@Path("grade")
public class Grade {

	@GET
//	@Produces("text/plain")
	@Produces(MediaType.APPLICATION_JSON)
	public String getIt(){
		SearchParam param = new SearchParam();
		param.setSwuID("x");
		param.setPassword("x");
		param.setXnm(2015);
		param.setXqm(1);
		
		Login login = new Login("x", "x");
		login.getBasicInfo();
		SwuGrades grades = new SwuGrades(login.getClient());
		String res =grades.lookup(param);
		System.out.println(res);
		
		return res;
	}
	
}
