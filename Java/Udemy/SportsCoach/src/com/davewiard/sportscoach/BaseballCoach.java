package com.davewiard.sportscoach;

public class BaseballCoach implements Coach {

    private PepTalkService pepTalkService;

    public BaseballCoach(PepTalkService pepTalkService) {
        this.pepTalkService = pepTalkService;
    }

    @Override
    public String getDailyWorkout() {
        return "Spend 30 minutes on batting practice";
    }

}
