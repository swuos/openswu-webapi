package com.swu.openswu.lostAndFound;

import com.swu.openswu.utils.ConnDb;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by csd on 2016/3/13.
 */
public class SimpleRegister implements Register {

    /*
        一个简单的信息登记类
     */

    protected final String insertStr = "" +
            "INSERT " +
            "INTO lostfind(swuid,text,details,time,place,done) " +
            "VALUES(?,?,?,?,?,?)";

    @Override
    public void register(Information informationAboutRegister) throws Throwable {
        try (Connection con = new ConnDb().getConnection()) {

            /*      动态sql语句插入数据库     */

            PreparedStatement pstmt = con.prepareStatement(insertStr);

            pstmt.setString(1, informationAboutRegister.getSwuid());
            pstmt.setString(2, ((WhoWantToRegister) informationAboutRegister).getText());
            pstmt.setString(3, ((WhoWantToRegister) informationAboutRegister).getDetails());
            pstmt.setString(4, ((WhoWantToRegister) informationAboutRegister).getTime());
            pstmt.setString(5, ((WhoWantToRegister) informationAboutRegister).getPlace());
            pstmt.setInt(6, informationAboutRegister.getDone());

            pstmt.executeUpdate();


        } catch (IOException e) {

            e.printStackTrace();

        } catch (SQLException e) {
            //重新抛出并记录日志
            e.printStackTrace();
            Throwable newExcep = new SQLException(" database connection error ");
            newExcep.initCause(e);

            throw newExcep;
        }
    }
}
