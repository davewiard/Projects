package com.davewiard.sportscoach;

public class TrackCoach implements Coach {

    private PepTalkService pepTalkService;

    public TrackCoach(PepTalkService pepTalkService) {
        this.pepTalkService = pepTalkService;
    }

    @Override
    public String getDailyWorkout() {
        return "Run a hard 5k";
    }
}
