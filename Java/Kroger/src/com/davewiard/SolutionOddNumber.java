package com.davewiard;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SolutionOddNumber {
    private static final Scanner scanner = new Scanner(System.in);


    static int[] oddNumbers(int l, int r) {
        /*
         * Write your code here.
         */

        // create new array that is approximate size needed
        int[] result = new int[((r-l) / 2) + 1];

        for (int i = l, j = 0; i <= r; i++) {
            if (i % 2 != 0) {
                // add to results array
                result[j++] = i;
            }
        }


        return result;
    }


    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int l = scanner.nextInt();
//        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        int r = scanner.nextInt();
//        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        int[] res = oddNumbers(l, r);
        for (int i : res) {
            System.out.println(i);
        }

        for (int resItr = 0; resItr < res.length; resItr++) {
            bufferedWriter.write(String.valueOf(res[resItr]));

            if (resItr != res.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
