package main.java.com.ilyabuglakov.daysofmonth.service;

import java.time.YearMonth;

/**
 * <h1>MonthStatisticService</h1>
 * The MonthStatisticService implements service class,
 * that gathers information about a specific month.
 * To specify exact month, methods require year, as int, and month number.
 *
 * @author Ilya Buglakov
 * @version 1.0
 * @since 2020-09-18
 */
public class MonthStatisticService {

    /**
     * This method is useful to find out,
     * how many days are in a specific month.
     * @param year - year as int
     * @param month - month number as int
     * @return - days in month as int
     */
    public int getDays(int year, int month){
        return YearMonth.of(year, month).lengthOfMonth();
    }

}
