package com.davewiard;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.stream.IntStream;

public class SolutionFindNumber {

    private static final Scanner scanner = new Scanner(System.in);

    static String findNumber(int[] arr, int k) {
        /*
         * Write your code here.
         */
        boolean result = false;
        for (int item : arr) {
            if (item == k) {
                result = true;
                break;
            }
        }
        return result ? "YES" : "NO";
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int arrCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        int[] arr = new int[arrCount];

        for (int arrItr = 0; arrItr < arrCount; arrItr++) {
            int arrItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");
            arr[arrItr] = arrItem;
        }

        int k = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        String res = findNumber(arr, k);

        bufferedWriter.write(res);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
