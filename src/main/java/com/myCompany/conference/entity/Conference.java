package com.myCompany.conference.entity;

import java.sql.Timestamp;
import java.util.List;

public class Conference extends AbstractEntity<Long> {
    private String title;
    private Timestamp timeConduction;
    private String venue;
    private List<Review> reviewList;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Timestamp getTimeConduction() {
        return timeConduction;
    }
    public void setTimeConduction(Timestamp timeConduction) {
        this.timeConduction = timeConduction;
    }
    public String getVenue() {
        return venue;
    }
    public void setVenue(String venue) {
        this.venue = venue;
    }
    public List<Review> getReviewList() {
        return reviewList;
    }
    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }
}
