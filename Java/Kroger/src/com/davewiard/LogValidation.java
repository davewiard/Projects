package com.davewiard;

import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LogValidation {
    static String regexPattern = "\"type\":\"([A-Z]+)\",\"userID\":\"([0-9]+)\",\"messageID\":\"([0-9A-Za-z]+)\",\"statusCode\":([0-9]+)";
    static Pattern pattern = Pattern.compile(regexPattern);

    static Map<String, List<String>> validateLogs(List<Log> logs) {

        Map<String, List<String>> result = new HashMap<>();

        for (Log log : logs) {
            System.out.println(log);
        }

        return result;
    }

    private static class Log {
        String type;
        String userID;
        String messageID;
        int statusCode;
    }

    public static void main(String args[] ) throws Exception {
        Scanner input = new Scanner(System.in);
        List<Log> logs = new ArrayList<>();
        do {
            Log log = new Log();
            Matcher matcher = pattern.matcher(input.nextLine());
            while (matcher.find()) {
                log.type = matcher.group(1);
                log.userID = matcher.group(2);
                log.messageID = matcher.group(3);
                log.statusCode = Integer.parseInt(matcher.group(4));
            }
            logs.add(log);
        } while(input.hasNext());
        printLogResults(validateLogs(logs));
    }

    public static void printLogResults(Map<String, List<String>> processedLogs) {
        StringBuffer sb = new StringBuffer();
        sb.append("{").append("\"ERROR\":[\"").append(processedLogs.get("ERROR").stream().collect(Collectors.joining("\",\""))).append("\"],");
        sb.append("\"INFO\":[\"").append(processedLogs.get("INFO").stream().collect(Collectors.joining("\",\""))).append("\"],");
        sb.append("\"PERFORMANCE\":[\"").append(processedLogs.get("PERFORMANCE").stream().collect(Collectors.joining("\",\""))).append("\"]");
        sb.append("}");
        System.out.println(sb.toString());
    }
}
