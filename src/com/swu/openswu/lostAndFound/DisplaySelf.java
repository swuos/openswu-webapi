package com.swu.openswu.lostAndFound;

import com.sun.istack.internal.Nullable;
import com.swu.openswu.utils.ConnDb;
import com.swu.openswu.utils.ResultSetToJson;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by csd on 2016/3/16.
 */
public final class DisplaySelf implements Display {

    private DisplaySelf() {
    }

    private String swuid;

    private ResultSet resultSet;
    private ResultSetToJson resultSetToJson;


    public DisplaySelf(@Nullable String swuid) {
        this.swuid = swuid;
        resultSetToJson = new ResultSetToJson();
    }

    private final String displayStr = "" +
            "SELECT * " +
            "FROM lostfind where swuid LIKE ?";

    @Override
    public String display() throws Throwable {

        try (Connection con = new ConnDb().getConnection()
        ) {

//            Statement statement = con.createStatement();
//            resultSet = statement.executeQuery(this.displayStr);

            PreparedStatement pstmt = con.prepareStatement(displayStr);

            if (this.swuid == null) {
                pstmt.setString(1, "%");
            } else {
                pstmt.setString(1, this.swuid);
            }
            resultSet = pstmt.executeQuery();


            return resultSetToJson.resultSetToJson(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
            Throwable newExc = new SQLException("database error in display");
            newExc.initCause(e);
            throw newExc;
        } catch (IOException e) {
            e.printStackTrace();
            Throwable newExc = new IOException("IO error in display");
            newExc.initCause(e);
            throw newExc;
        }

    }

}
