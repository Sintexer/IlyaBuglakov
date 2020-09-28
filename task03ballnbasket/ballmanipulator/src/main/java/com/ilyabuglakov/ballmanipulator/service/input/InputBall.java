package main.java.com.ilyabuglakov.ballmanipulator.service.input;

import main.java.com.ilyabuglakov.ballmanipulator.model.ball.Ball;
import main.java.com.ilyabuglakov.ballmanipulator.model.ball.BallColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InputBall {
    private final Scanner input;

    public InputBall(Scanner input) {
        this.input = input;
    }

    public Ball getBall() {
        return getBall(0, 0);
    }

    public Ball getBall(double weightBound, int costBound) {
        double weight;
        int cost;
        String color;

        Map<String, BallColor> colorMap = Arrays.stream(BallColor.values())
                .collect(Collectors.toMap(String::valueOf, Function.identity()));
        List<String> colors = new ArrayList<>(colorMap.keySet().size());
        colors.addAll(colorMap.keySet());

        CheckedInput checkedInput = new CheckedInput(input);
        checkedInput.setOnWrongInput(() -> System.out.println("Wrong input"));

        System.out.println("Input ball weight (greater " + weightBound + "): ");
        weight = checkedInput.checkedDouble(0);

        System.out.println("Input ball cost (greater " + costBound + "): ");
        cost = checkedInput.checkedInt(0);

        System.out.println("Input color index: ");
        for (int i = 0; i < colors.size(); ++i) {
            System.out.println((i + 1) + ". " + colors.get(i));
        }
        int index = checkedInput.checkedInt(0, colors.size() + 1);
        color = colors.get(index - 1);
        return new Ball(colorMap.get(color), weight, cost);
    }

}
