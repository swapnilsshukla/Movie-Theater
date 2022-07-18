package com.jpmc.theater;

import java.time.LocalDateTime;

public class Showing {
    public LocalDateTime getShowStartTime() {
        return showStartTime;
    }

    private Movie movie;
    private int sequenceOfTheDay;
    private LocalDateTime showStartTime;

    public Showing(Movie movie, int sequenceOfTheDay, LocalDateTime showStartTime) {
        this.movie = movie;
        this.sequenceOfTheDay = sequenceOfTheDay;
        this.showStartTime = showStartTime;

    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getStartTime() {
        return showStartTime;
    }

    public boolean isSequence(int sequence) {
        return this.sequenceOfTheDay == sequence;
    }

    public double getMovieFee() {
        return movie.getTicketPrice();
    }
    public double calculateTicketPrice() {
        return movie.getTicketPrice()-DiscountUtils.getDiscount(this);
    }

    public int getSequenceOfTheDay() {
        return sequenceOfTheDay;
    }

    public double calculateFee(int audienceCount) {
        return calculateTicketPrice() * audienceCount;
    }
}
