package com.swu.openswu.lostAndFound;

import com.swu.openswu.utils.ConnDb;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by csd on 2016/3/13.
 */
public class SimpleWithDraw implements Withdraw {

    /*
       一个简单的信息撤销类
     */
    private final String withdrawStr = " ";

    @Override
    public void withdraw(Imformation imformationAboutWithDraw) throws Throwable {
        try (Connection con = new ConnDb().getConnection()
        ) {


        } catch (SQLException e) {
            //重新抛出并记录日志
            e.printStackTrace();
            Throwable newExcep = new SQLException(" database connection error ");
            newExcep.initCause(e);
            throw newExcep;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
