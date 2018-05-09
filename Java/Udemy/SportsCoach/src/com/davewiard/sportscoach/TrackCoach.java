package com.davewiard.sportscoach;

public class TrackCoach implements Coach {

    private PepTalkService pepTalkService;

    public TrackCoach(PepTalkService pepTalkService) {
        this.pepTalkService = pepTalkService;
    }

    public TrackCoach() {

    }

    @Override
    public String getDailyWorkout() {
        return "Run a hard 5k";
    }

    @Override
    public String getDailyPepTalk() {
        // use my pepTalkService to get a pep talk
        return "Just do It: " + pepTalkService.getPepTalk();
    }
}
