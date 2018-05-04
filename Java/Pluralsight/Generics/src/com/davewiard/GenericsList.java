package com.davewiard;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GenericsList {

    public static void main(String[] args) {
        Person donDraper = new Person("Don Draper", 89);
        Person peggyOlson = new Person("Peggy Olson", 65);

        System.out.println("Creating ArrayList of Person objects");
        List<Person> madMen = new ArrayList<>();
        madMen.add(donDraper);
        madMen.add(peggyOlson);
        System.out.println(madMen);

        System.out.println("");
        System.out.println("Adding a new Person object");
        madMen.add(new Person("Bert Cooper", 100));
        System.out.println(madMen);
        System.out.println("Array size = " + madMen.size());

        System.out.println("");
        System.out.println("Using for");
        for (int i = 0; i < madMen.size(); i++) {
            System.out.println(madMen.get(i));
        }

        System.out.println("");
        System.out.println("Using foreach");
        for (Person person : madMen) {
            System.out.println(person);
        }

        System.out.println("");
        System.out.println("Using iterator");
        final Iterator<Person> iterator = madMen.iterator();
        while (iterator.hasNext()) {
            final Person person = iterator.next();
            System.out.println(person);
        }
    }
}
