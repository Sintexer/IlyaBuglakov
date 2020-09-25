import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class ArgsCalculator {
    public static final String ARGS_FORMAT = "Use pattern: (-s: sum||-m: multiplication) [numbers: any double]...";

    private static Map<String, BinaryOperator<Double>> operations;

    static {
        operations = new HashMap<>();
        operations.put("-s", (num1, num2) -> num1 + num2);
        operations.put("-m", (num1, num2) -> num1 * num2);
    }

    public static void main(String[] args) {

        String operationKey = args[0];
        if (args.length < 2 || !operations.keySet().contains(operationKey)) {
            printWrongArgsMessage();
            return;
        }

        Double result;
        try {
            List<Double> numbers = convertStringsToDoubles(Arrays.copyOfRange(args, 1, args.length));
            result = numbers.stream()
                    .reduce(operations.get(operationKey))
                    .get();
        } catch (NumberFormatException e) {
            printWrongArgsMessage();
            return;
        }
        System.out.println(result);
    }

    private static void printWrongArgsMessage() {
        System.out.println("Wrong argument('s)");
        System.out.println(ARGS_FORMAT);
    }

    private static List<Double> convertStringsToDoubles(String... numbers) throws NumberFormatException {
        return Arrays.stream(numbers)
                .map(Double::valueOf)
                .collect(Collectors.toList());
    }
}