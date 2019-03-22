package com.myCompany.conference.entity;

public class Speaker extends User{
    private long rating;
    private long bonus;

    public long getRating() {
        return rating;
    }
    public void setRating(long rating) {
        this.rating = rating;
    }
    public long getBonus() {
        return bonus;
    }
    public void setBonus(long bonus) {
        this.bonus = bonus;
    }

    public Speaker() {
    }

    public Speaker(User user, long rating, long bonus) {
        super(user.getId(),user.getEmail(), user.getPassword(), user.getName(), user.getSurname(), Role.Speaker);
        this.rating = rating;
        this.bonus = bonus;
    }
}
