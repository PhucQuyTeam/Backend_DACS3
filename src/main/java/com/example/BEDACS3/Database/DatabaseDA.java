package com.example.BEDACS3.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseDA {

    private static final String URL = "jdbc:mysql://localhost:3306/shopcathuysinh";
    private static final String USER = "root";
    private static final String PASS = "phucdeptrai123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}