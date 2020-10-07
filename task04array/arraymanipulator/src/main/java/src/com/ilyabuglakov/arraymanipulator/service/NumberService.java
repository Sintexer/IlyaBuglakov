package src.com.ilyabuglakov.arraymanipulator.service;

import java.math.BigInteger;

public class NumberService {

    public boolean isSimple(int number) {
        return BigInteger.valueOf(number).isProbablePrime((int) Math.log(number));
    }

    public boolean isFib(int number) {
        int prev = 1;
        int temp;
        for (int i = 1; i <= number; ) {
            if (i == number)
                return true;
            temp = i;
            i += prev;
            prev = temp;
        }
        return false;
    }

    public boolean isThreeDigitUnique(int number) {
        String string = String.valueOf(number);
        if (string.length() != 3)
            return false;
        return string.chars().distinct().count() == 3;
    }

}
