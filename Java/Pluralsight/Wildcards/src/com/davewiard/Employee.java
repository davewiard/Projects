package com.davewiard;

public class Employee extends Person {

    Employee(final String name, final Integer age) {
        super(name, age);
    }

    @Override
    public String toString() {
        return "Employee{" + "name='" + getName() + '\'' + ", age=" + getAge() + '}';
    }
}
