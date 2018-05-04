package com.davewiard;

import java.util.HashSet;
import java.util.Set;

public class GenericsSet {
    public static void main(String[] args) {
        Person donDraper = new Person("Don Draper", 89);
        Person peggyOlson = new Person("Peggy Olson", 65);
        Person bertCooper = new Person("Bert Cooper", 101);

        System.out.println("Creating HashSet...");
        Set<Person> madMen = new HashSet<>();
        madMen.add(donDraper);
        madMen.add(peggyOlson);
        madMen.add(donDraper);

        System.out.println("");
        System.out.println("Using foreach...");
        for (Person person : madMen) {
            System.out.println(person);
        }

        System.out.println("");
        System.out.println("Using contains...");
        System.out.println("Set contains Don Draper? " + madMen.contains(donDraper));
        System.out.println("Set contains Bert Cooper? " + madMen.contains(bertCooper));
    }
}
