package com.davewiard.sportscoach;

public class GolfCoach implements Coach {

    private PepTalkService pepTalkService;

    private String emailAddress;
    private String team;

    public GolfCoach() {
        System.out.println("GolfCoach: inside no-arg constructor");
    }

    @Override
    public String getDailyPepTalk() {
        return "The Ryder Cup is upon us! " + pepTalkService.getPepTalk();
    }

    @Override
    public String getDailyWorkout() {
        return "Practice with a large bucket of balls.";
    }

    public String getEmailAddress() {
        System.out.println("GolfCoach: inside getter method - getEmailAddress");
        return emailAddress;
    }

    public String getTeam() {
        System.out.println("GolfCoach: inside getter method - getTeam");
        return team;
    }

    // setter called by Spring during dependency injection
    public void setEmailAddress(String emailAddress) {
        System.out.println("GolfCoach: inside setter method - setEmailAddress");
        this.emailAddress = emailAddress;
    }

    public void setPepTalkService(PepTalkService pepTalkService) {
        System.out.println("GolfCoach: inside setter method - setPepTalkService");
        this.pepTalkService = pepTalkService;
    }

    public void setTeam(String team) {
        System.out.println("GolfCoach: inside setter method - setTeam");
        this.team = team;
    }

}
