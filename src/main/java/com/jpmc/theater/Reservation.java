package com.jpmc.theater;

import java.time.LocalDate;

public class Reservation {
    private Customer customer;
    private Showing showing;
    private int audienceCount;
    private LocalDate reservationDate;

    public Customer getCustomer() {
        return customer;
    }

    public Showing getShowing() {
        return showing;
    }

    public int getAudienceCount() {
        return audienceCount;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public Reservation(Customer customer, Showing showing, int audienceCount, LocalDate reservationDate) {
        this.customer = customer;
        this.showing = showing;
        this.audienceCount = audienceCount;
        this.reservationDate = reservationDate;
    }


    public double totalFee() {
        return (showing.getMovieFee() - DiscountUtils.getDiscount(this)) * audienceCount;
    }
}