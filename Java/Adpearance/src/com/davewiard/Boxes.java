package com.davewiard;

import java.util.HashMap;
import java.util.Map;

public class Boxes {
    private static final int largeBoxFits = 5;
    private static final int smallBoxFits = 1;

    private static final String largeBoxName = "large";
    private static final String smallBoxName = "small";

    public static int minimalNumberOfBoxes(int products, int availableLargeBoxes, int availableSmallBoxes) {

        int totalBoxesUsed = 0;

        HashMap<String, Integer> boxesNeeded= new HashMap<>();
        Integer used;

        while (products > 0) {
            if (products >= largeBoxFits) {
                used = boxesNeeded.get(largeBoxName);
                System.out.println("large used: " + used);
                if (used == null) used = 0;
                boxesNeeded.put(largeBoxName, used + 1);
                products -= largeBoxFits;
            } else if (products >= smallBoxFits) {
                used = boxesNeeded.get(smallBoxName);
                System.out.println("smallused: " + used);
                if (used == null) used = 0;
                boxesNeeded.put(smallBoxName, used + 1);
                products -= smallBoxFits;
            }
        }

        if (boxesNeeded.get(largeBoxName) > availableLargeBoxes ||
                boxesNeeded.get(smallBoxName) > availableSmallBoxes) {
            return -1;
        }

        for (String key : boxesNeeded.keySet()) {
            System.out.println("key: " + key + ", used: " + boxesNeeded.get(key));
            totalBoxesUsed += boxesNeeded.get(key);
        }

        return totalBoxesUsed;

    }

    public static void main(String[] args) {
//        System.out.println(minimalNumberOfBoxes(16, 2, 4));
//        System.out.println(minimalNumberOfBoxes(1, 2, 10));
//        System.out.println(minimalNumberOfBoxes(16, 2, 10));
        System.out.println(minimalNumberOfBoxes(16, 2, 10));
    }
}
