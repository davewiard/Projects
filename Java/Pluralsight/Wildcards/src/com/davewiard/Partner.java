package com.davewiard;

public class Partner extends Person{
    Partner(final String name, final Integer age) {
        super(name, age);
    }

    @Override
    public String toString() {
        return "Partner{" + "name='" + getName() + '\'' + ", age=" + getAge() + '}';
    }
}
