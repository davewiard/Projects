package com.davewiard.sportscoach;

public class TennisCoach implements Coach {

    private PepTalkService pepTalkService;

    public TennisCoach(PepTalkService pepTalkService) {
        this.pepTalkService = pepTalkService;
    }

    @Override
    public String getDailyWorkout() {
        return "Do 30 up/down drills.";
    }
}
