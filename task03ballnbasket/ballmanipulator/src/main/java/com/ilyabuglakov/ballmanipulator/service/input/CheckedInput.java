package main.java.com.ilyabuglakov.ballmanipulator.service.input;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Implements the methods of repetitive input.
 * If inputted information is wrong, another information will be requested.
 * Handles input exceptions.
 * Uses Scanner as the source of input.
 *
 * @author Ilya Buglakov
 * @version 1.0
 * @since 2020-09-28
 */
public class CheckedInput {

    private final Scanner input;
    private Runnable onWrongInput;

    /**
     * Initialises the source of input
     * @param input - source of input information
     */
    public CheckedInput(Scanner input) {
        this.input = input;
    }

    /**
     * Specifies the action will be launched at wrong input.
     * @param onWrongInput - Runnable action
     */
    public void setOnWrongInput(Runnable onWrongInput) {
        this.onWrongInput = onWrongInput;
    }

    /**
     * Tries to read int from Scanner input field. If the information is out of bounds
     * or different type, onWrongInput action will be launched and
     * another input operation from Scanner input field will be requested.
     * @param leftBound - the int value, inputted int is must be greater than.
     * @param rightBound - the int value, inputted int must be lesser than.
     * @return int - correct requested int.
     */
    public int checkedInt(int leftBound, int rightBound) {
        boolean correct = false;
        int inputtedInt = 0;
        do {
            try {
                inputtedInt = input.nextInt();

                if (inputtedInt >= leftBound && inputtedInt < rightBound) {
                    correct = true;
                }
            } catch (InputMismatchException e) {
                input.nextLine(); //Skips wrong input information
            }
            if (!correct && !Objects.isNull(onWrongInput)) {
                onWrongInput.run();
            }
        } while (!correct);
        return inputtedInt;
    }

    /**
     * Clarification of checkedInt(int leftBound, int rightBound) method.
     * Calls checkedInt(int leftBound, int rightBound)
     * and specifies the right bound as Integer.MAX_VALUE.
     * @param leftBound - the int value, inputted int is must be greater than.
     * @return int - correct requested int.
     */
    public int checkedInt(int leftBound) {
        return checkedInt(leftBound, Integer.MAX_VALUE);
    }


    /**
     * Tries to read double from Scanner input field. If the information is out of bounds
     * or different type, onWrongInput action will be launched and
     * another input operation from Scanner input field will be requested.
     * @param leftBound - the double value, inputted double is must be greater than.
     * @param rightBound - the double value, inputted double must be lesser than.
     * @return double - correct requested double.
     */
    public double checkedDouble(double leftBound, double rightBound) {
        boolean correct = false;
        double inputtedDouble = 0;
        while (!correct) {
            try {
                inputtedDouble = input.nextDouble();
                if (Double.compare(inputtedDouble, leftBound)>=0 && Double.compare(inputtedDouble, rightBound) <=0) {
                    correct = true;
                }
            } catch (InputMismatchException e) {
                input.nextLine(); //Skips wrong input information
            }
            if (!correct && !Objects.isNull(onWrongInput)) {
                onWrongInput.run();
            }
        }
        return inputtedDouble;
    }

    /**
     * Clarification of checkedDouble(double leftBound, double rightBound) method.
     * Calls checkedDouble(double leftBound, double rightBound)
     * and specifies the right bound as Double.MAX_VALUE.
     * @param leftBound - the double value, inputted double is must be greater than.
     * @return double - correct requested double.
     */
    public double checkedDouble(double leftBound) {
        return checkedDouble(leftBound, Double.MAX_VALUE);
    }

    /**
     * Tries to read double from Scanner input field. If the information is out of bounds
     * or different type, onWrongInput action will be launched and
     * another input operation from Scanner input field will be requested.
     * @param leftBound - the BigDecimal value, inputted BigDecimal is must be greater than.
     * @param rightBound - the BigDecimal value, inputted BigDecimal must be lesser than.
     * @return BigDecimal - correct requested BigDecimal.
     */
    public BigDecimal checkedBigDecimal(BigDecimal leftBound, BigDecimal rightBound) {
        boolean correct = false;
        BigDecimal inputtedDecimal = BigDecimal.ZERO;
        while (!correct) {
            try {
                inputtedDecimal = BigDecimal.valueOf(input.nextDouble());
                if (inputtedDecimal.compareTo(leftBound)>=0 &&  inputtedDecimal.compareTo(rightBound)<=0) {
                    correct = true;
                }
            } catch (InputMismatchException e) {
                input.nextLine(); //Skips wrong input information
            }
            if (!correct && !Objects.isNull(onWrongInput)) {
                onWrongInput.run();
            }
        }
        return inputtedDecimal;
    }

    /**
     * Clarification of checkedBigDecimal(BigDecimal leftBound, BigDecimal rightBound) method.
     * Calls checkedBigDecimal(BigDecimal leftBound, BigDecimal rightBound)
     * and specifies the right bound as Double.MAX_VALUE.
     * @param leftBound - the BigDecimal value, inputted BigDecimal is must be greater than.
     * @return BigDecimal - correct requested BigDecimal.
     */
    public BigDecimal checkedBigDecimal(BigDecimal leftBound) {
        return checkedBigDecimal(leftBound, BigDecimal.valueOf(Double.MAX_VALUE));
    }



}
