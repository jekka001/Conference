package com.myCompany.conference.entity;

import java.util.List;

public class Review extends AbstractEntity<Long> {
    private String topic;
    private List<User> registered;
    private int visitors;
    private Speaker speaker;
    private StateReview state;

    public String getTopic() {
        return topic;
    }
    public void setTopic(String topic) {
        this.topic = topic;
    }
    public List<User> getRegistered() {
        return registered;
    }
    public int getIntRegistered(){
        return registered.size();
    }
    public void setRegistered(List<User> registered) {
        this.registered = registered;
    }
    public int getVisitors() {
        return visitors;
    }
    public void setVisitors(int visitors) {
        this.visitors = visitors;
    }
    public Speaker getSpeaker() {
        return speaker;
    }
    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }
    public StateReview getState() {
        return state;
    }
    public void setState(StateReview state) {
        this.state = state;
    }

    public Review() {
    }

    public Review(Long id, String topic, List<User> registered, int visitors, Speaker speaker, StateReview state) {
        setId(id);
        this.topic = topic;
        this.registered = registered;
        this.visitors = visitors;
        this.speaker = speaker;
        this.state = state;
    }
}
