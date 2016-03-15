package com.swu.openswu.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by csd on 2016/3/14.
 */
public class ConnDb {

    public static Connection getConnection() throws IOException, SQLException {

        Properties props = new Properties();

        try (InputStream in = Files.newInputStream(Paths.get("database.properties"));
        ) {
            props.load(in);
        }

        String drivers = props.getProperty("jdbc.drivers");
        if (drivers != null) System.setProperty("jdbc.drivers", drivers);

        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");

        return DriverManager.getConnection(url, username, password);

    }

}
