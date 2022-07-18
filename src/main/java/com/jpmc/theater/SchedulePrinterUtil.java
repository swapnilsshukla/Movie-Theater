package com.jpmc.theater;

import com.google.gson.Gson;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.jpmc.theater.Constants.PRINT_FORMAT_JSON;
import static com.jpmc.theater.Constants.PRINT_FORMAT_TEXT;

public class SchedulePrinterUtil {
    private static  String humanReadableFormat(Duration duration) {
        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());
        return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
    }

    private static  String humanReadableFormatJson(Duration duration) {
        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());
        return String.format("%s hour%s %s minute%s", hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
    }

    // (s) postfix should be added to handle plural correctly
    private static String handlePlural(long value) {
        if (value == 1) {
            return "";
        }
        else {
            return "s";
        }
    }
    public static void printSchedule(LocalDateProvider provider, List<Showing> showings, String format) throws Exception {
        if(PRINT_FORMAT_TEXT.equals(format)){
            printScheduleText( provider,  showings);
        }else if(PRINT_FORMAT_JSON.equals(format)){
            printJson(provider,  showings);
        }else{
            throw new Exception("Invalid format type");
        }
    }
    public static void printScheduleText(LocalDateProvider provider, List<Showing> schedule) {
        System.out.println(provider.currentDate());
        System.out.println("===================================================");
        schedule.forEach(s ->
                System.out.println(s.getSequenceOfTheDay() + ": " + s.getStartTime() + " " + s.getMovie().getTitle() + " " + humanReadableFormat(s.getMovie().getRunningTime()) + " $" + s.getMovieFee())
        );
        System.out.println("===================================================");
    }
    private static void  printJson(LocalDateProvider provider, List<Showing> schedule){
        Gson gson = new Gson();
        System.out.println(provider.currentDate());
        System.out.println("===================================================");
        System.out.println("[");
        schedule.forEach(s -> {
                    printJsonSchedule(s);
                }
        );
        System.out.println("]");
        System.out.println("===================================================");
    }

    private static void  printJsonSchedule(Showing showing){
        System.out.println("\t{");
        System.out.println("\t \t \"sequenceOfTheDay\" : "+ showing.getSequenceOfTheDay() +",");
        System.out.println("\t \t \"startTime\" : \""+ showing.getStartTime() +"\",");
        System.out.println("\t \t \"movieName\" : \""+ showing.getMovie().getTitle() +"\",");
        System.out.println("\t \t \"runTime\" : \""+ humanReadableFormatJson(showing.getMovie().getRunningTime()) +"\",");
        System.out.println("\t \t \"ticketPrice\" :"+ showing.getMovie().getTicketPrice() );
        System.out.println("\t}");
    }

    public static void printSchedule(Theater theater,String type) throws Exception {
        printSchedule(theater.getProvider(),theater.getSchedule(),type);
    }
}
