public class Reverser {

    private final static String ARGS_FROMAT = "Use pattern: [\"1st_arg\"] [\"2nd_arg\"] ... [\"Nth_arg\"]";

    public static void main(String... args) {
    	if(args.length==0)
			System.out.println(ARGS_FROMAT);
        for (int i = args.length - 1; i >= 0; --i) {
            System.out.print(args[i] + " ");
        }
    }
}