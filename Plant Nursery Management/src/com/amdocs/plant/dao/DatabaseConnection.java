package com.amdocs.plant.dao;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                // Load the MySQL JDBC driver
                Class.forName("oracle.jdbc.driver.OracleDriver"); // Registration
                System.out.println("Inside try after class.forname");
                conn = DriverManager.getConnection("Jdbc:Oracle:thin:@LAPTOP-J257HKO2:1521:XE", "system", "admin");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
