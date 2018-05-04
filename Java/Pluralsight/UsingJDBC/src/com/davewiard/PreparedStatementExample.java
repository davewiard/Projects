package com.davewiard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class PreparedStatementExample {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://localhost/msp";
        Properties props = new Properties();
        props.setProperty("user", "stock");
        props.setProperty("password", "stock");
        props.setProperty("ssl", "false");
        try (
                Connection conn = DriverManager.getConnection(url, props);
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM trade WHERE symbol_id = ? ORDER BY trade_date DESC");
        ) {
            String format = "%-4s%-10s%10s\n";

            // select symbol_id = 2
            pstmt.setInt(1, 2);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.format(format, rs.getString("id"), rs.getString("trade_date"), rs.getString("shares"));
            }
        } catch (SQLException e) {
            System.err.println("Failed to retrieve data");
            System.err.println(e.getMessage());
        }
    }
}
