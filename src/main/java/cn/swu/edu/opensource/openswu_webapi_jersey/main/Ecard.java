package cn.swu.edu.opensource.openswu_webapi_jersey.main;

import cn.swu.edu.opensource.openswu_webapi_jersey.ecard.EcardParam;
import cn.swu.edu.opensource.openswu_webapi_jersey.utils.Lookup;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glassfish.jersey.server.ContainerRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * Created by 西南大学开源协会 陈思定  on 2016/8/23.
 * <p>
 * Email : sidingchan@gmail.com
 */

@Path("ecard")
public class Ecard {

    private static Log LOGGER = LogFactory.getLog(QuitNet.class);

    @Context
    ContainerRequest cr;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getIt(EcardParam ecardParam) {


        Lookup ecardLookup;

        return null;
    }


}
