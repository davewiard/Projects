package com.davewiard;

import java.sql.*;
import java.sql.PreparedStatement;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://localhost/msp";
        Properties props = new Properties();
        props.setProperty("user", "stock");
        props.setProperty("password", "stock");
        props.setProperty("ssl", "false");
        try (
                Connection conn = DriverManager.getConnection(url, props);
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM trade WHERE symbol_id = ?");
            )
        {
            pstmt.setInt(1, 2);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("Connection successful.");
        } catch (SQLException e) {
            System.err.println("Connection failed.");
        }
    }
}
