package com.swu.openswu.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by csd on 2016/3/14.
 */
public class ConnDb {

    public Connection getConnection() throws IOException, SQLException {

        Properties props = new Properties();


        try (InputStream in = this.getClass().getResourceAsStream("/resource/database.properties");
        ) {
            props.load(in);
        }
        //相同效果 以此保证运行时读取配置文件路径是正确的。
//        try (InputStream in = this.getClass().getResource("/resource/database.properties").openStream() ;
//        ) {
//            props.load(in);
//        }


        String driver = props.getProperty("jdbc.driver");
        if (driver != null) {
            System.setProperty("jdbc.drivers", driver);
        }

        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");

        return DriverManager.getConnection(url, username, password);

    }

}
