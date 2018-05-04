package com.davewiard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Person donDraper = new Person("Don Draper", 89);
        Person peggyOlson = new Person("Peggy Olson", 65);
        Person bertCooper = new Person("Bert Cooper", 101);

        List<Person> madMen = new ArrayList<>();
        madMen.add(donDraper);
        madMen.add(peggyOlson);
        madMen.add(bertCooper);

        System.out.println("Initial sorting order ...");
        System.out.println(madMen);

        System.out.println("");
        System.out.println("Sorted by age order ...");
        Collections.sort(madMen, new AgeComparator());
        System.out.println(madMen);

        System.out.println("");
        System.out.println("Sorted by age reversed order ...");
        Collections.sort(madMen, new ReverseComparator<>(new AgeComparator()));
        System.out.println(madMen);
    }
}
