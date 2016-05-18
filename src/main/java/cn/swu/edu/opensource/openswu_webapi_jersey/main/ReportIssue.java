package cn.swu.edu.opensource.openswu_webapi_jersey.main;

import cn.swu.edu.opensource.openswu_webapi_jersey.auth.SecurityFilter;
import cn.swu.edu.opensource.openswu_webapi_jersey.issues.Issue;
import org.glassfish.jersey.server.ContainerRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by 西南大学开源协会 陈思定  on 2016/5/18.
 * <p>
 * Email : sidingchan@gmail.com
 */


@Path("reportIssue")
public class ReportIssue {

    @Context
    ContainerRequest cr;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt(Issue issue) {

        /* 认证 */
        new SecurityFilter().filter(cr);

//        URL url = this.getClass().getClassLoader().getResource(".");
//
//        File issuesFile = null;
//        try {
//            issuesFile = new File(url.toURI());
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }

        File issuesFile = new File("issues.txt");

        if (issuesFile.exists()) {
            /* 往后追加
            * swuid   issue
            * */
            try (FileWriter fileWriter = new FileWriter(issuesFile,true)){
                fileWriter.write(issue.getSwuID()+"    "+issue.getIssue()+"\n");
                fileWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            /* 解决可能在文件不存在时可能出现的并发问题*/
            synchronized(issuesFile){
                if (issuesFile.exists()){
                    try (FileWriter fileWriter = new FileWriter(issuesFile,true)){
                        fileWriter.write(issue.getSwuID()+"    "+issue.getIssue()+"\n");
                        fileWriter.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        issuesFile.createNewFile();
                        try(FileWriter fileWriter = new FileWriter(issuesFile,true)) {
                            fileWriter.write(issue.getSwuID()+"    "+issue.getIssue()+"\n");
                            fileWriter.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


        return "Thanks for your report.";
    }
}
