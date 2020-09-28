package main.java.com.ilyabuglakov.ballmanipulator.service.input;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class CheckedInput {

    private final Scanner input;
    private Runnable onWrongInput;

    public CheckedInput(Scanner input) {
        this.input = input;
    }

    public void setOnWrongInput(Runnable onWrongInput) {
        this.onWrongInput = onWrongInput;
    }

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
                input.nextLine();
            }
            if (!correct && !Objects.isNull(onWrongInput)) {
                onWrongInput.run();
            }
        } while (!correct);
        return inputtedInt;
    }

    public int checkedInt(int leftBound) {
        return checkedInt(leftBound, Integer.MAX_VALUE);
    }

    public double checkedDouble(double leftBound, double rightBound) {
        boolean correct = false;
        double inputtedDouble = 0;
        while (!correct) {
            try {
                inputtedDouble = input.nextDouble();
                if (inputtedDouble > leftBound && inputtedDouble < rightBound) {
                    correct = true;
                }
            } catch (InputMismatchException e) {
                input.nextLine();
            }
            if (!correct && !Objects.isNull(onWrongInput)) {
                onWrongInput.run();
            }
        }
        return inputtedDouble;
    }

    public double checkedDouble(double leftBound) {
        return checkedDouble(leftBound, Double.MAX_VALUE);
    }

}
