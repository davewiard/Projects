package com.davewiard.sportscoach;

public class BaseballCoach implements Coach {

    // define a private field for the dependency
    private PepTalkService pepTalkService;

    // define a constructor for dependency injection
    public BaseballCoach(PepTalkService pepTalkService) {
        this.pepTalkService = pepTalkService;
    }

    public BaseballCoach() {

    }

    @Override
    public String getDailyWorkout() {
        return "Spend 30 minutes on batting practice";
    }

    @Override
    public String getDailyPepTalk() {
        // use my pepTalkService to get a pep talk
        return "Boys of October! " + pepTalkService.getPepTalk();
    }

}
