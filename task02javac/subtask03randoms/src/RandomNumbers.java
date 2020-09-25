import java.util.Random;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class RandomNumbers{

    private static final String NEW_LINE_OPTION = "-n";
    private static final String ARGS_FORMAT = "Use pattern: java RandomNumbers [-n - new line] (positive int - amount of random numbers)";
    private static final String NUMBER_PATTERN = "[+]?\\d+";

    public static void main(String[] args) {
        RandomNumbers randomNumbers = new RandomNumbers();
        if(!randomNumbers.checkArgs(args)){
            showArgsFormat();
            return;
        }
        IntConsumer action;
        String randomsAmount;
        if(args.length==2)
            randomsAmount = args[1];
        else
            randomsAmount = args[0];
        action = randomNumbers.evaluateAction(args);
        try {
            Random random = new Random();
            IntStream.generate(random::nextInt)
                    .limit(Integer.parseInt(randomsAmount))
                    .forEach(action);
        }catch (NumberFormatException e){
            showArgsFormat();
        }
    }

    private IntConsumer evaluateAction(String... args){
        if(args[0].equals(NEW_LINE_OPTION))
            return System.out::println;
        return (number) -> System.out.print(number+" ");
    }

    private boolean checkArgs(String... args){
        if(args.length==2){
            if(args[0].equals(NEW_LINE_OPTION) && args[1].matches(NUMBER_PATTERN))
                return true;
        } else if (args.length==1){
            if(args[0].matches(NUMBER_PATTERN))
                return true;
        }
        return false;
    }

    private static void showArgsFormat(){
        System.out.println(ARGS_FORMAT);
    }
}