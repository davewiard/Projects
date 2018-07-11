package com.davewiard;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static LocalDateTime startDateTime;
    static LocalDateTime endDateTime;

    public static void main(String[] args) {

        try {
            // read start/end times from user
            startDateTime = getLocalDateTime("start");
            endDateTime = getLocalDateTime("end");
            System.out.println("LocalDateTime start: " + startDateTime);
            System.out.println("LocalDateTime end: " + endDateTime);

            // read data from file(s)
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        scanner.close();
    }


    /**
     *
     * @return
     */
    private static LocalDateTime getLocalDateTime(String startOrEnd) {
        LocalDateTime localDateTime;

        try {
            System.out.print("Enter " + startOrEnd + " date (YYYY-MM-DD): ");
            String dateString = scanner.next();
            System.out.print("Enter " + startOrEnd + " time (24H:MM:SS): ");
            String timeString = scanner.next();

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(dateString, formatter);
                System.out.println("input: " + startOrEnd + " date: " + dateString + ", LocalDate: " + localDate);

                formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalTime localTime = LocalTime.parse(timeString, formatter);
                System.out.println("input: " + startOrEnd + " time: " + timeString + ", LocalTime: " + localTime);

                localDateTime = LocalDateTime.of(localDate, localTime);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return localDateTime;
    }

}
