package com.davewiard;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    LocalDateTime startDateTime;
    LocalDateTime endDateTime;

    public static void main(String[] args) {
        String startDateString;
        String startTimeString;
        String endDateString;
        String endTimeString;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter starting date (YYYY-MM-DD): ");
        startDateString = scanner.next();
        System.out.print("Enter starting time (24H:MM:SS): ");
        startTimeString = scanner.next();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startDate = LocalDate.parse(startDateString, formatter);
            System.out.println("input: start date: " + startDateString + ", LocalDate: " + startDate);

            formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime startTime = LocalTime.parse(startTimeString, formatter);
            System.out.println("input: start time: " + startTimeString + ", LocalTime: " + startTime);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        System.out.print("Enter end date (YYYY-MM-DD): ");
        endDateString = scanner.next();
        System.out.print("Enter end time (24H:MM:SS): ");
        endTimeString = scanner.next();

        try {
            LocalDate startDate = getLocalDate(startDateString);
            LocalDate endDate = getLocalDate(endDateString);

            LocalTime startTime = getLocalTime(startTimeString);
            LocalTime endTime = getLocalTime(endTimeString);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }


    /**
     *
     * @return
     */
    public static LocalDateTime getLocalDateTime() {
        LocalDateTime localDateTime = null;



        return localDateTime;
    }


    /**
     *
     * @param inputDate
     * @return
     */
    public static LocalDate getLocalDate(String inputDate) {
        LocalDate localDate = null;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd");
            localDate = LocalDate.parse(inputDate, formatter);
            System.out.println("input: date string: " + inputDate + ", LocalDate: " + localDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return localDate;
    }

    public static LocalTime getLocalTime(String inputTime) {
        LocalTime localTime = null;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            localTime = LocalTime.parse(inputTime, formatter);
            System.out.println("input: time string: " + inputTime + ", LocalTime: " + localTime);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return localTime;
    }

}
