package com.davewiard;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List list = new ArrayList<>();
        list.add("abc");
        list.add(1);
        list.add(new Object());

        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        legacyMethod(integers);

        List param;

        List <String> strings = list;

        for (String elem : strings) {
            System.out.println(elem);
        }

    }

    public static void legacyMethod(List list) {
        System.out.println("inside legacyMethod");
    }


/*
 * This code does not use any Rawtypes
 *
    public static void main(String[] args) {

	    List list = new ArrayList();
	    list.add("abc");
	    list.add(1);
	    list.add(new Object());

        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            final Object element = iterator.next();
            System.out.println(element);
        }
    }
*/
}
