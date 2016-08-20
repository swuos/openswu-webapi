package log;

/**
 * Created by 西南大学开源协会 陈思定  on 2016/8/20.
 * <p>
 * Email : sidingchan@gmail.com
 */

import org.apache.commons.logging.Log;

import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;

public class TestLog {

    Log log = LogFactory.getLog(this.getClass());


    public void one() {

        log.info("into one method");

        try {

            System.out.println(9 / 0);

        } catch (RuntimeException e) {

            log.error(e.getMessage());

        }

        log.info("out one method");

    }


    public static void main(String[] args) {

        PropertyConfigurator.configure("./log4j.properties");

        new TestLog().one();

    }
}
