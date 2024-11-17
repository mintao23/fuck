package org.example.mintao.util;

import java.sql.*;

public class JDBCUtil {
    private static Connection conn = null;

    // Method to establish and return a database connection
    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) { // Check if connection is null or closed
                Class.forName("org.mariadb.jdbc.Driver"); // Load MariaDB driver
                conn = DriverManager.getConnection(
                        "jdbc:mariadb://walab.handong.edu:3306/OSS24_22300266",
                        "OSS24_22300266",
                        "Seeh0Ieh"
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    // Close ResultSet, PreparedStatement, and Connection
    public static void close(ResultSet rs, PreparedStatement pst, Connection conn) {
        try {
            if (rs != null) rs.close(); // Close ResultSet
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (pst != null) pst.close(); // Close PreparedStatement
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (conn != null && !conn.isClosed()) conn.close(); // Close Connection
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Close PreparedStatement and Connection
    public static void close(PreparedStatement pst, Connection conn) {
        try {
            if (pst != null) pst.close(); // Close PreparedStatement
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (conn != null && !conn.isClosed()) conn.close(); // Close Connection
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
