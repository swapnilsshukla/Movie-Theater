package com.jpmc.theater;

import java.time.LocalDateTime;

import static com.jpmc.theater.Constants.*;

public class DiscountUtils {

    public static double getDiscount(Reservation reservstion){
        double dateDiscount = reservstion.getReservationDate().getDayOfMonth() == 7 ? 1.0: 0.0;
        double showingDiscount = getDiscount(reservstion.getShowing());
        return Math.max(dateDiscount,showingDiscount);
    }

    public static double getDiscount(Showing showing){
        double sequenceDiscount = 0;
        if (showing.getSequenceOfTheDay() == FIRST_OF_DAY) {
            sequenceDiscount = 3;
        } else if (showing.getSequenceOfTheDay() == SECOND_OF_DAY) {
            sequenceDiscount = 2;
        }

        double specialDiscount = getDiscount(showing.getMovie());

        double timeDiscount = getDiscount(showing.getStartTime(), showing.getMovie().getTicketPrice());


        return Math.max(sequenceDiscount,Math.max(specialDiscount,timeDiscount));
    }

    public static double getDiscount(Movie movie){
            double specialDiscount = 0;
            if (Movie.getMovieCodeSpecial() == movie.getSpecialCode()) {
                specialDiscount = movie.getTicketPrice() * SPECIAL_CODE_DISCOUNT;// 20% discount for special movie
            }
            return specialDiscount;
    }

    public static double getDiscount(LocalDateTime startTime, double ticketPrice){
        double timeDiscount = 0;
        if(startTime.getHour()>=11 && startTime.getHour()<=16)
            timeDiscount = ticketPrice * TIME_BASED_DISCOUNT;

        return timeDiscount;
    }

}
