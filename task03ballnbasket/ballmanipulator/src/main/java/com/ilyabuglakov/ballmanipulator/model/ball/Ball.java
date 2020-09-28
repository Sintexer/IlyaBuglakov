package main.java.com.ilyabuglakov.ballmanipulator.model.ball;

import java.util.Comparator;
import java.util.Objects;

/**
 * Ball is a class, which represents the real ball.
 * Ball is immutable class. Class instances must be created via Constructor.
 * It has such characteristics, as weight, cost and color.
 * Class Ball overrides equals() and hashCode().
 * Class implements Comparable<Ball>
 *
 * @author Ilya Buglakov
 * @version 1.0
 * @since 2020-09-28
 */
public class Ball implements Comparable<Ball> {

    private final BallColor color;
    private final double weight;
    private final int cost;

    /**
     * Single existing Constructor of Ball
     * @param color - BallColor - represents the color of ball
     * @param weight - weight of ball as double
     * @param cost - the cost of ball
     */
    public Ball(BallColor color, double weight, int cost) {
        this.color = color;
        this.weight = weight;
        this.cost = cost;
    }

    /**
     * Provide the possibility to add class entities to the Collections
     * @param o - object to compare with
     * @return - true, if objects are the same by fields, otherwise false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ball ball = (Ball) o;
        return Double.compare(ball.weight, weight) == 0 &&
                cost == ball.cost &&
                color == ball.color;
    }

    /**
     * Provides the possibility to add class entities to the HashMap
     * and other hash-oriented structures.
     * @return - hash code, created from each field
     */
    @Override
    public int hashCode() {
        return Objects.hash(color, weight, cost);
    }

    /**
     * Consist of information from every field.
     * @return String with information about class entity.
     */
    @Override
    public String toString() {
        return "Ball{" +
                "color=" + color +
                ", weight=" + weight +
                ", cost=" + cost +
                '}';
    }

    /**
     * Provides the possibility to compare class entities using comparator.
     * @param o - ball to compare with.
     * @return int, that represents comparison by natural order.
     */
    @Override
    public int compareTo(Ball o) {
        return Comparator.comparingDouble(Ball::getWeight)
                .thenComparingInt(Ball::getCost)
                .thenComparing(Ball::getColor)
                .compare(this, o);
    }

    public BallColor getColor() {
        return color;
    }

    public double getWeight() {
        return weight;
    }

    public int getCost() {
        return cost;
    }

}
