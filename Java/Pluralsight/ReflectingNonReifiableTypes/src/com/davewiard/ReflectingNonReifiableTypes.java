package com.davewiard;

import java.util.ArrayList;
import java.util.List;

public class ReflectingNonReifiableTypes<T> {

    public static void main(String[] args) {
        new ReflectingNonReifiableTypes<String>().main();
    }

    private void main() {
        // Type T is not reifiable so an error is caught by IDE
//        System.out.println(T.class);

        List<String> strings = new ArrayList<>();
        List<Integer> integers = new ArrayList<>();
        System.out.println(strings.getClass());
        System.out.println(integers.getClass());
        System.out.println(integers.getClass() == strings.getClass());

        List<? extends Number> numbers = new ArrayList<>();
        System.out.println(numbers.getClass());
        System.out.println(integers.getClass() == numbers.getClass());
    }
}
