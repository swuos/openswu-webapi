package cn.swu.edu.opensource.openswu_webapi_jersey.main;

import cn.swu.edu.opensource.openswu_webapi_jersey.library.LibraryParameter;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by 西南大学开源协会 陈思定  on 2016/8/31.
 * <p>
 * Email : sidingchan@gmail.com
 * <p>
 * 查询图书馆的信息。
 */

@Path("/library/{module}")
public class Library {

    @PathParam("module")
    @DefaultValue("borrowInfo")
    String module;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt(LibraryParameter libraryParameter) {

        switch (module) {
            case "borrowInfo":
                System.out.println("borrowInfo");
                break;
            case "history":
                System.out.println("history");
                break;
            default:
                System.out.println("default");
                break;
        }


        return "return";
    }
}
