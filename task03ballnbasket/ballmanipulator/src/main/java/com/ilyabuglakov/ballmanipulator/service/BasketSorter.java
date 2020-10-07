package main.java.com.ilyabuglakov.ballmanipulator.service;

import main.java.com.ilyabuglakov.ballmanipulator.model.ball.Ball;
import main.java.com.ilyabuglakov.ballmanipulator.repository.Basket;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BasketSorter {

    /**
     * Creates a new basket, balls of which are sorted by the omparator.
     * @param basket - initial basket.
     * @return new basket with sorted by cost balls.
     */
    public Basket sort(Basket basket, Comparator<Ball> comparator){
       BasketCreator creator = new BasketCreator();
        List<Ball> sortedBalls = (basket.getBalls());
        sortedBalls = sortedBalls.stream()
                        .sorted(comparator)
                        .collect(Collectors.toList());
        return creator.createBasket(sortedBalls);
    }
}
