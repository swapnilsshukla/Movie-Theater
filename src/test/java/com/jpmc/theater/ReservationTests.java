package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.jpmc.theater.Constants.FIRST_OF_DAY;
import static com.jpmc.theater.Constants.SECOND_OF_DAY;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservationTests {

    @Test
    void totalFee() {
        var customer = new Customer("John Doe", "unused-id");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.0, 1),
                FIRST_OF_DAY,
                LocalDateTime.now()
        );
        assertTrue(new Reservation(customer, showing, 3,LocalDateProvider.singleton().currentDate()).totalFee() == 27 );
    }

    @Test
    void totalFeeSeqOne() {
        var customer = new Customer("John Doe", "unused-id");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.0, 0),
                FIRST_OF_DAY,
                LocalDateTime.of(LocalDateProvider.singleton().currentDate(), LocalTime.of(9, 0))
        );
        assertTrue(new Reservation(customer, showing, 3,LocalDateProvider.singleton().currentDate()).totalFee() == 27 );
    }
    @Test
    void totalSeqTwo() {
        var customer = new Customer("John Doe", "unused-id");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.0, 0),
                SECOND_OF_DAY,
                LocalDateTime.of(LocalDateProvider.singleton().currentDate(), LocalTime.of(9, 0))
        );
        assertTrue(new Reservation(customer, showing, 3,LocalDateProvider.singleton().currentDate()).totalFee() == 30 );
    }

    @Test
    void totalFeeDateSeven() {
        var customer = new Customer("John Doe", "unused-id");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10.0, 0),
                5,
                LocalDateTime.of(LocalDateProvider.singleton().currentDate(), LocalTime.of(9, 0))
        );
        assertTrue(new Reservation(customer, showing, 2,LocalDate.parse("2022-06-07")).totalFee() == 18 );
    }

    @Test
    void totalFeeBestDiscount() {
        var customer = new Customer("John Doe", "unused-id");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10.0, 1),
                FIRST_OF_DAY,
                LocalDateTime.of(LocalDateProvider.singleton().currentDate(), LocalTime.of(12, 0))
        );
        assertTrue(new Reservation(customer, showing, 2,LocalDate.parse("2022-06-07")).totalFee() == 14 );
    }

    @Test
    void totalFeeBestDiscountTwo() {
        var customer = new Customer("John Doe", "unused-id");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 20.0, 1),
                FIRST_OF_DAY,
                LocalDateTime.of(LocalDateProvider.singleton().currentDate(), LocalTime.of(12, 0))
        );
        assertTrue(new Reservation(customer, showing, 2,LocalDate.parse("2022-06-07")).totalFee() == 30 );
    }

}
