package com.davewiard;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

public class PersonalizedCoupons {

    static List<Map<String, Object>> personalizeCoupons(List<Map<String, Object>> coupons,
                                                        List<String> preferredCategories) {

        List<Map<String, Object>> topCouponsList = new ArrayList<>();
        List<Map<String, Object>> topCouponsSorted = new ArrayList<>();

        float[] topPercentage = new float[10];  // keep a running list of the best percentages
        int topCouponCount = 0;

        // loop through input coupons and filter out the categories that do not apply
        for (Map<String, Object> object : coupons) {

            for (String category : preferredCategories) {
                if (object.get("category").equals(category)) {
                    // this will filter the list of categories down to just the preferred
                    boolean keepCoupon = false;

                    float percentage = (float)object.get("couponAmount") / (float)object.get("itemPrice");
                    System.out.println("percentage: " + percentage);
                    for (int i = 0; i < topPercentage.length; i++) {
                        if (topPercentage[i] < percentage) {
                            // decide whether to keep this coupon or not
                            if (topCouponCount == 10) {
                                // the percentages array is full and the current array item
                                // is less than the current coupon, keep this coupon
                                keepCoupon = true;
                            } else if (topPercentage[i] == 0) {
                                // the percentages array is not filled yet, keep this coupon
                                keepCoupon = true;
                            }

                            if (keepCoupon) {
                                System.out.println("keeping: " + object);
                                object.put("percentage", percentage);
                                object.remove("code");
                                topCouponsList.add(object);
                                topPercentage[i] = percentage;
                                topCouponCount++;
                                break;
                            }
                        }
                    }
                }
            }
        }

//        for (Map<String, Object> coupon : topCouponsList) {
//            System.out.println(coupon);
//            System.out.println("----------");
//        }

        // sort the top coupons list
        Arrays.sort(topPercentage);
        for (Map<String, Object> coupon : topCouponsList) {
            for (int i = topPercentage.length - 1; i >= 0; i--) {
                System.out.println(coupon);
                coupon.remove("percentage");
                topCouponsSorted.add(coupon);
                break;
            }
        }

        for (Map<String, Object> coupon : topCouponsSorted) {
            System.out.println(coupon.get("couponAmount"));
        }
        return topCouponsSorted;
    }

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        List<String> preferredCategories = Arrays.asList(input.nextLine().split(","));
        List<Map<String, Object>> coupons = new ArrayList<>();
        int lines = Integer.parseInt(input.nextLine());
        IntStream.range(0, lines).forEach(i -> coupons.add(readCoupon(input)));
        List<Map<String, Object>> personalizedCoupons = personalizeCoupons(coupons, preferredCategories);
        personalizedCoupons.stream().forEach(PersonalizedCoupons::printCoupon);
    }

    public static Map<String, Object> readCoupon(Scanner input) {
        String[] couponItems = input.nextLine().split(",");
        Map<String,Object> coupon = new HashMap<>();
        coupon.put("upc", couponItems[0]);
        coupon.put("code", couponItems[1]);
        coupon.put("category", couponItems[2]);
        coupon.put("itemPrice", Float.parseFloat(couponItems[3]));
        coupon.put("couponAmount", Float.parseFloat(couponItems[4]));
        return coupon;
    }

    public static void printCoupon(Map<String, Object> coupon)
    {
        System.out.print("{");
        System.out.print("\"couponAmount\":" +  coupon.get("couponAmount") + ",");
        System.out.print("\"upc\":\"" +  coupon.get("upc") + "\",");
        if(coupon.containsKey("code")) {
            System.out.print("\"code\":\"" +  coupon.get("code") + "\",");
        }
        System.out.print("\"itemPrice\":" +  coupon.get("itemPrice") + ",");
        System.out.println("\"category\":\"" +  coupon.get("category") + "\"}");
    }
}
