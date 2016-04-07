package com.swu.openswu.lostAndFound;

import com.swu.openswu.utils.ConnDb;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by csd on 2016/3/13.
 */
public class SimpleWithDraw implements Withdraw {

    /*
       一个简单的信息撤销类
     */
    private final String withdrawStr = "" +
            "UPDATE lostfind " +
            "SET done='1' " +
            "WHERE id=?";

    @Override
    public void withdraw(Information informationAboutWithDraw) throws Throwable {
        try (Connection con = new ConnDb().getConnection()
        ) {

            PreparedStatement pstmt = con.prepareStatement(withdrawStr);

            pstmt.setString(1, ((WhoWantToWithDraw) informationAboutWithDraw).getId());

            pstmt.executeUpdate();

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
