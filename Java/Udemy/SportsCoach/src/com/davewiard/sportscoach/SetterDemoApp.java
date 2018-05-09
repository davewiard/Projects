package com.davewiard.sportscoach;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SetterDemoApp {

    public static void main(String[] args) {

        // load the Spring configuration file
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        // retrieve bean from Spring container
        GolfCoach theCoach = context.getBean("myGolfCoach", GolfCoach.class);

        // call methods on the bean
        System.out.println(theCoach.getDailyWorkout());
        System.out.println(theCoach.getDailyPepTalk());
        System.out.println(theCoach.getTeam());
        System.out.println(theCoach.getEmailAddress());

        // close the context
        context.close();
    }
}
