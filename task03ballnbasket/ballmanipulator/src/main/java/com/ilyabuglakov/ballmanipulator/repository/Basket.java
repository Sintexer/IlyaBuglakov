package main.java.com.ilyabuglakov.ballmanipulator.repository;

import main.java.com.ilyabuglakov.ballmanipulator.model.ball.Ball;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Basket is a wrapper class, contains list collection to store Balls.
 * Implements equals and hashCode, so Basket class entities can be placed to containers.
 *
 * @author Ilya Buglakov
 * @version 1.0
 * @since 2020-09-28
 */
public class Basket {

    private List<Ball> balls = new ArrayList<>();

    /**
     * Creates Basket entity from List<Ball>
     * @param balls - list of balls
     * @return new Basket entity
     */
    public static Basket of(List<Ball> balls) {
        Basket basket = new Basket();
        basket.getBalls().addAll(balls);
        return basket;
    }

    public List<Ball> getBalls() {
        return balls;
    }

    /**
     * Adds ball to list.
     * @param ball - ball to add.
     */
    public void add(Ball ball) {
        balls.add(ball);
    }

    public void addAll(Ball... balls) {
        this.balls.addAll(Arrays.asList(balls));
    }

    public void remove(Ball ball) {
        balls.remove(ball);
    }

    public Ball remove(int index) {
        return balls.remove(index);
    }

    /**
     * Provides the possibility to store Baskets in Collections.
     * @param o - object to compare with.
     * @return true, if Baskets are equal by their content.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Basket basket = (Basket) o;
        if (balls.size() != basket.getBalls().size())
            return false;
        for (int i = 0; i < balls.size(); ++i) {
            if (balls.get(i) != basket.getBalls().get(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(balls);
    }

    @Override
    public String toString() {
        return balls.toString();
    }
}
