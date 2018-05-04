package com.davewiard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ResultSetScrolling {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://localhost/msp";
        Properties props = new Properties();
        props.setProperty("user", "stock");
        props.setProperty("password", "stock");
        props.setProperty("ssl", "false");
        try (
                Connection conn = DriverManager.getConnection(url, props);
                Statement stmt = conn.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stmt.executeQuery("SELECT id,trade_date,shares FROM trade LIMIT 10");
        ) {
            String format = "%-4s%-10s%10s\n";
            rs.beforeFirst();
            System.out.println("First 10 Rows:");
            while (rs.next()) {
                System.out.format(format, rs.getString("id"), rs.getString("trade_date"), rs.getString("shares"));
            }

            rs.afterLast();
            System.out.println("Last 10 Rows:");
            while (rs.previous()) {
                System.out.format(format, rs.getString("id"), rs.getString("trade_date"), rs.getString("shares"));
            }

            rs.first();
            System.out.println("First Row:");
            System.out.format(format, rs.getString("id"), rs.getString("trade_date"), rs.getString("shares"));

            rs.last();
            System.out.println("Last Row:");
            System.out.format(format, rs.getString("id"), rs.getString("trade_date"), rs.getString("shares"));
        } catch (SQLException e) {
            System.err.println("Failed to retrieve data");
            System.err.println(e.getMessage());
        }
    }
}
