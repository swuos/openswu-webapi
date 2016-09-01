package cn.swu.edu.opensource.openswu_webapi_jersey.main;

import cn.swu.edu.opensource.openswu_webapi_jersey.ecard.EcardLookup;
import cn.swu.edu.opensource.openswu_webapi_jersey.ecard.EcardParameter;
import cn.swu.edu.opensource.openswu_webapi_jersey.utils.Lookup;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by 西南大学开源协会 陈思定  on 2016/8/23.
 * <p>
 * Email : sidingchan@gmail.com
 */

@Path("ecard")
public class Ecard {


    public Ecard() {
        ecardLookup = new EcardLookup();
    }

    private static Log LOGGER = LogFactory.getLog(QuitNet.class);

    private Lookup ecardLookup;
    private String response;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt(EcardParameter ecardParam) {

        LOGGER.info("Ecard => " + ecardParam.toString());


        long start = System.currentTimeMillis();
        response = ecardLookup.lookup(ecardParam);
        System.out.println("time : " + (System.currentTimeMillis() - start));


        return response == null ? "用户名或密码错误" : response;
    }


}
