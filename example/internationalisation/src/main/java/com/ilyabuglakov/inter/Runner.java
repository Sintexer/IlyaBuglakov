package main.java.com.ilyabuglakov.inter;

import java.util.Locale;
import java.util.ResourceBundle;

public class Runner {
    public static void main(String[] args) {
        Locale current = new Locale("ru", "RU");
        ResourceBundle rb = ResourceBundle.getBundle("property.text", current);
        String s1 = rb.getString("message.str1");
        System.out.println(s1);
    }
}
