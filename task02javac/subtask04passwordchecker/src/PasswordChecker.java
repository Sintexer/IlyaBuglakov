import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PasswordChecker{

    public static final String ARGS_FORMAT = "Use pattern: (password: String)";
    public static final String PASSWORDS_PATH = "../resources/passwords.txt";

    public static void main(String[] args) {
        if(args.length!=1) {
            System.out.println(ARGS_FORMAT);
            return;
        }
        String promotedPassword = args[0];
        String response = "Password declined";
        try{
            List<String> passwords = Files.readAllLines(Paths.get(PASSWORDS_PATH));
            if(passwords.contains(promotedPassword))
                response = "Password accepted";
        } catch(IOException e) {
            System.out.println("Can't read password from file");
        }


        System.out.println(response);
    }
}