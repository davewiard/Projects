package com.davewiard;

import java.util.HashMap;
import java.util.Map;

public class GenericsMap {

    public static void main(String[] args) {
        Person donDraper = new Person("Don Draper", 89);
        Person peggyOlson = new Person("Peggy Olson", 65);
        Person bertCooper = new Person("Bert Cooper", 101);

        System.out.println("Creating HashMap...");
        Map<String, Person> madMen = new HashMap<>();
        madMen.put(donDraper.getName(), donDraper);
        madMen.put(peggyOlson.getName(), peggyOlson);
        madMen.put(bertCooper.getName(), bertCooper);

        System.out.println("");
        System.out.println("Using object...");
        System.out.println(madMen);

        System.out.println("");
        System.out.println("Using get...");
        System.out.println(madMen.get("Don Draper"));

        System.out.println("");
        System.out.println("Using foreach keySet...");
        for (String name : madMen.keySet()) {
            System.out.println("Key = " + name);
        }

        System.out.println("");
        System.out.println("Using foreach values...");
        for (Person person : madMen.values()) {
            System.out.println("Value = " + person);
        }

        System.out.println("");
        System.out.println("Using foreach entrySet...");
        for (Map.Entry<String, Person> entry : madMen.entrySet()) {
            System.out.println("Key = " + entry.getKey() + "; " + "Value = " + entry.getValue());
        }
    }
}
