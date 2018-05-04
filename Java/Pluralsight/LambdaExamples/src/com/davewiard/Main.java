package com.davewiard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        final Person donDraper = new Person("Don Draper", 89);
        final Person peggyOlson = new Person("Peggy Olson", 65);
        final Person bertCooper = new Person("Bert Cooper", 101);

        Predicate<Person> isOld = person -> person.getAge() > 80;
        System.out.println(isOld.test(donDraper));
        System.out.println(isOld.test(peggyOlson));

        List<Person> people = new ArrayList<>();
        people.add(donDraper);
        people.add(peggyOlson);
        people.add(bertCooper);

        final Map<Boolean, List<Person>> oldAndYoungPeople =
                people.stream()
                      .collect(Collectors.partitioningBy(isOld));
        System.out.println(oldAndYoungPeople);

        final Map<Boolean, Long> oldAndYoungCount =
                people.stream()
                      .collect(Collectors.partitioningBy(isOld, Collectors.counting()));
        System.out.println(oldAndYoungCount);

    }
}
