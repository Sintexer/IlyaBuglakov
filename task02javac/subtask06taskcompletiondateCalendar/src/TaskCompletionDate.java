import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TaskCompletionDate{

    public static final String ARGS_FORMAT = "Use pattern: (developer name) (days needs to complete task: int)";
    public static final String DATE_FORMAT = "dd MMMMMM yyyy kk:mm:ss";

    public static void main(String[] args) {
        if(args.length!= 2){
            printWrongArgsMessage();
            return;
        }

        int daysNeeds;
        try {
            daysNeeds = Integer.parseInt(args[1]);
        } catch (NumberFormatException e){
            printWrongArgsMessage();
            return;
        }
        GregorianCalendar date = new GregorianCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        date.add(Calendar.DATE, daysNeeds);
        System.out.println("Developer " + args[0] + " needs " + daysNeeds + " days, to finish task."
                + "Task will be completed on "
                + dateFormat.format(date.getTime()));
    }

    private static String formatDate(Calendar date){
        return String.format(DATE_FORMAT, date.get(Calendar.DAY_OF_MONTH),
                date.get(Calendar.MONTH),
                date.get(Calendar.HOUR),
                date.get(Calendar.MINUTE),
                date.get(Calendar.SECOND));
    }

    private static void printWrongArgsMessage(){
        System.out.println("Wrong arg('s)");
        System.out.println(ARGS_FORMAT);
    }
}