package com.davewiard.sportscoach;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpringApp {

    public static void main(String[] args) {
        // load the Spring configuration file
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // retrieve bean from Spring container
        Coach theCoach = context.getBean("sportsCoach", Coach.class);

        // call methods on the bean
        System.out.println(theCoach.getDailyWorkout());

        // call our new pep talk method
        System.out.println(theCoach.getDailyPepTalk());

        // close the context
        context.close();
    }

}
