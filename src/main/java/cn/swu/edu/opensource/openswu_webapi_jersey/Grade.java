package cn.swu.edu.opensource.openswu_webapi_jersey;

import cn.swu.edu.opensource.openswu_webapi_jersey.auth.SecurityFilter;
import cn.swu.edu.opensource.openswu_webapi_jersey.grade.GradeData;
import cn.swu.edu.opensource.openswu_webapi_jersey.grade.SearchParam;
import cn.swu.edu.opensource.openswu_webapi_jersey.grade.SwuGrades;
import cn.swu.edu.opensource.openswu_webapi_jersey.grade.TotalInfo;
import cn.swu.edu.opensource.openswu_webapi_jersey.login.Login;
import com.google.gson.Gson;
import org.glassfish.jersey.server.ContainerRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**	
 *
 * @author 陈思定/chensiding@qq.com
 * 
 */
@Path("grade")
public class Grade {

	SecurityFilter filter = new SecurityFilter();
	//注入请求
	@Context
	ContainerRequest cr;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public GradeData getIt(SearchParam param) {
		filter.filter(cr);

		Login login = new Login(param.getSwuID(), param.getPassword());
		TotalInfo info = login.getBasicInfo();
		SwuGrades grades = new SwuGrades(login.getClient());
		Gson gson = new Gson();
		info.setGrades(gson.fromJson(grades.lookup(param), GradeData.class));

		return info.getGrades();
	}

}
