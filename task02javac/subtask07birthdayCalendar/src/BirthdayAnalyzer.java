import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class BirthdayAnalyzer {

    public static final String ARGS_FORMAT = "Use pattern: (year: int) (month: int from 1 to 12) (day: int from 1 to 31)";
    public static final String DATE_FORMAT = "dd MMMMMM yyyy kk:mm:ss";
    public static final String BIRTHDAY_MESSAGE = "HAPPY BIRTHDAY";

    public static final String[] weekDays = new String[]{"Sunday", "Monday", "Tuesday",
            "Wednesday", "Thursday", "Friday", "Saturday"};

    public static void main(String[] args) {
        if (args.length != 3) {
            printWrongArgsMessage();
            return;
        }

        int day;
        int month;
        int year;
        try {
            year = Integer.parseInt(args[0]);
            month = Integer.parseInt(args[1]);
            day = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            printWrongArgsMessage();
            return;
        }
        if (!checkDateArgs(year, month, day)) {
            printWrongArgsMessage();
            return;
        }

        GregorianCalendar date = new GregorianCalendar();
        date.set(year, month - 1, day);
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

        int yearsOld;
        GregorianCalendar dateInCurrentYear = (GregorianCalendar) date.clone();
        GregorianCalendar currentDate = new GregorianCalendar();
        dateInCurrentYear.set(Calendar.YEAR, currentDate.get(Calendar.YEAR));

        yearsOld = currentDate.get(Calendar.YEAR) - date.get(Calendar.YEAR);
        if (currentDate.before(dateInCurrentYear))
            --yearsOld;

        System.out.println("You were born on " + weekDays[date.get(Calendar.DAY_OF_WEEK)-1] + ".\n"
                + "And you are " + yearsOld + " years old.");
        if (currentDate.get(Calendar.MONTH) == date.get(Calendar.MONTH)
                && currentDate.get(Calendar.DATE) == date.get(Calendar.DATE)) {
            System.out.println(BIRTHDAY_MESSAGE);
        }
    }

    private static boolean checkDateArgs(int year, int month, int day) {
        return (year > 1800 && year <= Calendar.getInstance().get(Calendar.YEAR))
                && (month > 0 && month <= 12)
                && (day > 0 && day < 31);
    }

    private static void printWrongArgsMessage() {
        System.out.println("Wrong arg('s)");
        System.out.println(ARGS_FORMAT);
    }
}