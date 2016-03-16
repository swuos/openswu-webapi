package com.swu.openswu.lostAndFound;

import com.sun.istack.internal.Nullable;
import com.swu.openswu.utils.ConnDb;

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

    public DisplaySelf(@Nullable String swuid) {
        this.swuid = swuid;
    }


    private ResultSet resultSet;

    private final String displayStr = "" +
            "SELECT * " +
            "FROM openswu " +
            "WHERE swuid = ?";

    @Override
    public String display() throws Throwable {

        try (Connection con = new ConnDb().getConnection()
        ) {

            PreparedStatement pstmt = con.prepareStatement(displayStr);

            pstmt.setString(1, this.swuid);

            resultSet = pstmt.executeQuery();


        } catch (SQLException e) {
            e.printStackTrace();
            Throwable newExc = new SQLException("database error in display");
            newExc.initCause(e);
            throw newExc;
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}
