package com.davewiard.sportscoach;

public class TennisCoach implements Coach {

    private PepTalkService pepTalkService;

    public TennisCoach(PepTalkService pepTalkService) {
        this.pepTalkService = pepTalkService;
    }

    public TennisCoach() {

    }

    @Override
    public String getDailyWorkout() {
        return "Do 30 up/down drills.";
    }

    @Override
    public String getDailyPepTalk() {
        // use my pepTalkService to get a pep talk
        return pepTalkService.getPepTalk() + " Wimbledon starts next week!";
    }


}
