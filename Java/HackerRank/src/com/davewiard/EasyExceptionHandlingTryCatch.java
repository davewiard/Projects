package com.davewiard;

import java.util.Scanner;

public class EasyExceptionHandlingTryCatch {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            Integer int1 = scanner.nextInt();
            Integer int2 = scanner.nextInt();

            System.out.println(int1 / int2);
        } catch (ArithmeticException e) {
            System.out.println(e.getClass().getName() + ": / by zero");
        } catch (Exception e) {
            System.out.println(e.getClass().getName());
        }
    }
}
