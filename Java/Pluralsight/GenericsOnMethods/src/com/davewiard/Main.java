package com.davewiard;

import java.util.ArrayList;
import java.util.Comparator;
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

        /* the commented call to min uses the non-generic min() */
/*        final Person youngestCastMember = (Person) min(madMen, new AgeComparator());*/
        final Person youngestCastMember = min(madMen, new AgeComparator());
        System.out.println(youngestCastMember);

        List<Integer> numbers = new ArrayList<>();
        numbers.add(2);
        numbers.add(3);
        numbers.add(1);
        System.out.println(min(numbers, Integer::compare));
    }

    private static <T> T min(List<T> values, Comparator<T> comparator) {
        if (values.isEmpty()) {
            throw new IllegalArgumentException("List is empty, cannot find minimum");
        }

        T lowestElement = values.get(0);

        for (int i = 1; i < values.size(); i++) {
            final T element = values.get(i);
            if (comparator.compare(element, lowestElement) < 0) {
                lowestElement = element;
            }
        }

        return lowestElement;
    }

    /*
     * This version does not implement generics so it is not type-safe
     *
    public static Object min(List values, Comparator comparator) {
        if (values.isEmpty()) {
            throw new IllegalArgumentException("List is empty, cannot find minimum");
        }

        Object lowestElement = values.get(0);

        for (int i = 1; i < values.size(); i++) {
            final Object element = values.get(i);
            if (comparator.compare(element, lowestElement) < 0) {
                lowestElement = element;
            }
        }

        return lowestElement;
    }
    */
}
