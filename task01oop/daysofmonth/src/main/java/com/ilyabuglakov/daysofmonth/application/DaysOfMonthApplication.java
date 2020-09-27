package main.java.com.ilyabuglakov.daysofmonth.application;

import main.java.com.ilyabuglakov.daysofmonth.service.MonthStatisticService;

import java.time.DateTimeException;

/**
 * <h1>DaysOfMonthApplication</h1>
 * DaysOfMonthApplication is a class, used to parse command line args
 * and then find out, how many days are in certain month
 *
 * @author Ilya Buglakov
 * @version 1.0
 * @since 2020-09-18
 */
public class DaysOfMonthApplication {

    private DaysOfMonthApplication() {
    }

    /**
     * This is the main method, that parses args and
     * finds the amount of days in a specific month,
     * with the help of MonthStatisticService.getDays(year, month) method
     * args[0] - year as int, args[1] - month as int.
     */
    public static void main(String[] args) {
        MonthStatisticService service = new MonthStatisticService();
        if(args.length != 2) {
            printGuide();
            return;
        }

        try {
            int year = Integer.parseInt(args[0]);
            int month = Integer.parseInt(args[1]);
            System.out.println(service.getDays(year, month));
        }catch (DateTimeException | NumberFormatException e){
            System.err.println("Wrong arguments");
            printGuide();
        }
    }

    /**
     * Method shows user guide about correct command line args
     */
    private static void printGuide(){
        System.out.println("Correct args: [int: year] [int: month]");
    }
}
