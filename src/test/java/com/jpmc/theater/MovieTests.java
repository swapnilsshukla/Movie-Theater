package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTests {
    @Test
    void specialMovieWith50PercentDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),10, 1);
       // change this
        Showing showing = new Showing(spiderMan, 5,
                LocalDateTime.of(LocalDateProvider.singleton().currentDate(), LocalTime.of(9, 0)));
        assertEquals(8, showing.calculateTicketPrice());
        System.out.println(Duration.ofMinutes(90));
    }
}
