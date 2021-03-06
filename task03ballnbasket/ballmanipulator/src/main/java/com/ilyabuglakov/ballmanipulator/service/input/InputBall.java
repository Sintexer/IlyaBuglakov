package main.java.com.ilyabuglakov.ballmanipulator.service.input;

import main.java.com.ilyabuglakov.ballmanipulator.exception.BallCreationException;
import main.java.com.ilyabuglakov.ballmanipulator.model.ball.Ball;
import main.java.com.ilyabuglakov.ballmanipulator.model.ball.BallColor;
import main.java.com.ilyabuglakov.ballmanipulator.service.BallCreator;
import main.java.com.ilyabuglakov.ballmanipulator.service.BallCreatorValidator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Class InputBall implements the creation of Ball entity
 * by the information, inputted by user via terminal.
 * Information requests by the dialog through terminal.
 *
 * @author Ilya Buglakov
 * @version 1.0
 * @since 2020-09-28
 */
public class InputBall {
    private final Scanner input = new Scanner(System.in);

    /**
     * Clarification of getBall(double weightBound, int costBound) method.
     * Calls getBall(double weightBound, int costBound) and specifies
     * both parameters as zeros.
     * @return ball - Ball class entity, created by user.
     */
    public Ball getBall() throws BallCreationException {
        return getBall(0, 0);
    }

    /**
     * Method creates Ball class entity by information,
     * inputted by user via terminal. Method requires two parameters, that
     * represent bounds of ball weight and cost.
     * @param weightBound - weight of ball, inputted weight must be greater than.
     *                   Must be greater or equal to zero.
     * @param costBound - cost of ball, inputted cost must be greater than.
     *                   Must be greater or equal to zero.
     * @return ball - Ball class entity, created by user.
     */
    public Ball getBall(double weightBound, int costBound) throws BallCreationException {
        if(weightBound<0 || costBound<0) {
            throw new IllegalArgumentException("Input bounds must be greater than zero");
        }
        BigDecimal weight;
        BigDecimal cost;
        String colorName;
        BallCreatorValidator validator = new BallCreatorValidator();
        BallCreator creator = new BallCreator();

        Map<String, BallColor> colorMap = Arrays.stream(BallColor.values())
                .collect(Collectors.toMap(String::valueOf, Function.identity()));
        List<String> colors = new ArrayList<>(colorMap.keySet().size());
        colors.addAll(colorMap.keySet());

        CheckedInput checkedInput = new CheckedInput(input);
        checkedInput.setOnWrongInput(() -> System.out.println("Wrong input"));

        System.out.println("Input ball weight (greater " + weightBound + "): ");
        weight = checkedInput.checkedBigDecimal(BigDecimal.ZERO);

        System.out.println("Input ball cost (greater " + costBound + "): ");
        cost = checkedInput.checkedBigDecimal(BigDecimal.ZERO);

        System.out.println("Input color index: ");
        for (int i = 0; i < colors.size(); ++i) {
            System.out.println((i + 1) + ". " + colors.get(i));
        }
        int index = checkedInput.checkedInt(0, colors.size() + 1);
        colorName = colors.get(index - 1);
        BallColor color = colorMap.get(colorName);
        if(!validator.areValidArgs(weight, cost, color))
            throw new BallCreationException();
        return new Ball(color, weight, cost);
    }

}
