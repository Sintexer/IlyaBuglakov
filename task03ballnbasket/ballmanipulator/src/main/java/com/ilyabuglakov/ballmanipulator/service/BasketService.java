package main.java.com.ilyabuglakov.ballmanipulator.service;

import main.java.com.ilyabuglakov.ballmanipulator.model.ball.Ball;
import main.java.com.ilyabuglakov.ballmanipulator.model.ball.BallColor;
import main.java.com.ilyabuglakov.ballmanipulator.repository.Basket;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

//TO DO pattern command

/**
 * BasketService produces information on base of Baskets.
 *
 * @author Ilya Buglakov
 * @version 1.0
 * @since 2020-09-28
 */
public class BasketService {

    /**
     * Calculates the sum weight of all balls in the basket,
     * or returns zero, if basket is empty.
     * @param basket - basket, weight of what balls to find.
     * @return double - weight of all balls in basket.
     */
    public BigDecimal calculateBasketWeight(Basket basket) {
        return basket.getBalls().stream()
                .map(Ball::getWeight)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Counts the amount of balls with specific color in the basket.
     * @param basket - basket, amount of colored balls of which to find.
     * @param color - color of balls to count.
     * @return long - amount of balls with specific color in basket.
     */
    public long countColor(Basket basket, BallColor color) {
        return basket.getBalls().stream()
                .filter(ball -> ball.getColor().equals(color))
                .count();
    }

    /**
     * Counts the amount of same bas in the basket.
     * Balls are same, if all their field values are same.
     * @param basket - baskets, balls of which to count.
     * @return long - amount of same balls.
     */
    public long amountOfSameBalls(Basket basket) {
        return basket.getBalls().stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values()
                .stream()
                .filter(val -> val > 1)
                .reduce(0L, Long::sum);
    }

    /**
     * Counts the amount of baskets, equals by their content.
     * @param baskets - list of baskets to analise.
     * @return long - amount of same baskets.
     */
    public long amountOfSameBaskets(List<Basket> baskets) {
        return baskets.stream()
                .map(basket -> basket.getBalls().stream()
                        .sorted()
                        .collect(Collectors.toList()))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values()
                .stream()
                .filter(val -> val > 1)
                .reduce(0L, Long::sum);
    }

}
