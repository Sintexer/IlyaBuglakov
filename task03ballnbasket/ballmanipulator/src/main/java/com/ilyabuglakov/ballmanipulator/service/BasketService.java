package main.java.com.ilyabuglakov.ballmanipulator.service;

import main.java.com.ilyabuglakov.ballmanipulator.model.ball.Ball;
import main.java.com.ilyabuglakov.ballmanipulator.model.ball.BallColor;
import main.java.com.ilyabuglakov.ballmanipulator.repository.Basket;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BasketService {

    public double calculateBasketWeight(Basket basket) {
        return basket.getBalls().stream()
                .map(Ball::getWeight)
                .reduce(0.0, Double::sum);
    }

    public long countColor(Basket basket, BallColor color) {
        return basket.getBalls().stream()
                .filter(ball -> ball.getColor().equals(color))
                .count();
    }

    public long amountOfSameBalls(Basket basket) {
        return basket.getBalls().stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values()
                .stream()
                .filter(val -> val > 1)
                .reduce(0L, Long::sum);
    }

    public Basket sortedByCost(Basket basket) {
        Basket sorted = new Basket();
        sorted.getBalls()
                .addAll(basket.getBalls().stream()
                        .sorted(Comparator.comparing(Ball::getCost))
                        .collect(Collectors.toList()));
        return sorted;
    }

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
