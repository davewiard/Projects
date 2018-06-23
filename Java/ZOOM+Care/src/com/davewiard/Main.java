package com.davewiard;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Main {

    static String zoomApiUrl = "https://www.zoomcare.com/zoom-api/schedule";
    static String contentJson;
    static HashSet<String> clinicSet;
    static JSONObject json;
    static ServiceSchedules serviceSchedulesAvailable = new ServiceSchedules();


    /**
     * Main program entry point
     * @param args String path to the input JSON file used for POST requests
     */
    public static void main(String... args) {
        Path path = Paths.get(args[0]);

        // read POST JSON data from input file
        try {
            contentJson = new String(Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // configure the serviceSchedulesAvailable input data
        serviceSchedulesAvailable.setZoomApiUrl(zoomApiUrl);
        serviceSchedulesAvailable.setPostData(contentJson);

        try {
            // retrieve the data from the ZOOM+Care API
            json = serviceSchedulesAvailable.updateServiceSchedulesFromAPI();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // get the complete set of clinics which could be spread across multiple
        // serviceSchedules entries in the JSON returned from the API
        clinicSet = serviceSchedulesAvailable.getClinicCodes();

        // print the list of clinicCode values to the screen
        List<String> clinicArray = new ArrayList<>(clinicSet);
        for (int i = 0; i < clinicArray.size(); i++) {
            System.out.println((i + 1) + ": " + clinicArray.get(i));
        }

        System.out.println();
        System.out.println("0: Exit");

        System.out.println();
        System.out.println("Enter a number to select a clinic:");

        Integer selected = 0;
        while (selected >= 0) {
            selected = getUserInput(clinicArray.size());
            if (selected == 0) {
                break;
            }

            // decrement for use as an index into the clinicCodes array
            --selected;

            // get the full set of clinic hours
            JSONArray clinicHours = serviceSchedulesAvailable.getClinicHours(clinicArray.get(selected));

            // find and print the open/closed hours for the previously found clinic
            //
            // ASSUMPTION:
            // It is assumed that the request was made specifically for today's schedule
            // It will not match past or future schedules, as-is
            int dayOfWeek = getCurrentDayOfWeek();
            for (int i = 0; i < clinicHours.length(); i++) {
                if (clinicHours.getJSONObject(i).getInt("dayOfWeek") == dayOfWeek) {
                    String openHour = String.valueOf(clinicHours.getJSONObject(i).getInt("openHour"));
                    String closeHour = String.valueOf(clinicHours.getJSONObject(i).getInt("closeHour"));

                    // create the strings for open and close hours in AM/PM format
                    try {
                        openHour = getFormattedHour(openHour);
                        closeHour = getFormattedHour(closeHour);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    System.out.printf(">open at %s; closed at %s\n", openHour, closeHour);
                }
            }

        }

    }


    /**
     *
     * @param hour
     * @return
     */
    static String getFormattedHour(String hour) {
        String result = null;

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h");
            Date date = simpleDateFormat.parse(hour);
            result = new SimpleDateFormat("ha").format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    /**
     * Gets the current day of the week. Java uses '1' to mean Monday. ZOOM+Care
     * API returns '1' to mean Sunday. Whatever gets returned by Java needs to be
     * shifted by 1 to compensate which opens a slot for Sunday as '1'.
     * @return The current day of the week represented with Sunday = '1', Monday = '2', ...
     */
    public static int getCurrentDayOfWeek() {
        int dayOfWeek = LocalDate.now().getDayOfWeek().getValue() + 1;

        // Java        Sunday == 7, Monday = 1
        // ZOOM+Care   Sunday == 1, Monday = 2
        if (dayOfWeek > DayOfWeek.SUNDAY.getValue()) {
            dayOfWeek = 1;
        }

        return dayOfWeek;
    }


    /**
     *
     * @param max Integer maximum to accept as valid input.
     * @return Integer representing the index of the clientCode being selected.
     */
    public static Integer getUserInput(Integer max) {
        Integer selected = 0;

        while (true) {
            // read selection from the user
            System.out.print("> ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            try {
                selected = Integer.parseInt(input);
                if (selected == 0) {
                    // terminates the while loop and continues execution
                    break;
                } else if (selected > max || selected < 0) {
                    System.out.println("'" + input + "' is not a valid selection. Please try again.");
                    System.out.println();
                } else {
                    // valid integer within the required range entered
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("'" + input + "' is not a number. Please try again.");
                System.out.println();
            }
        }

        return selected;
    }
}
