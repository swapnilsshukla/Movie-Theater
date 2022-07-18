package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import static com.jpmc.theater.Constants.SECOND_OF_DAY;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TheaterTests {
    @Test
    void totalFeeForCustomer() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, SECOND_OF_DAY, 4);
        //System.out.println("You have to pay " + reservation.totalFee());
        assertEquals(reservation.totalFee(), 37.5);
    }

    @Test
    void printMovieSchedule() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        try {
            SchedulePrinterUtil.printSchedule(theater,"JSON");
            SchedulePrinterUtil.printSchedule(theater,"TEXT");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
