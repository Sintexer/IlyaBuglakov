package main.java.com.ilyabuglakov.ballmanipulator.model.ball;

import java.util.Comparator;
import java.util.Objects;

public class Ball implements Comparable<Ball> {

    private final BallColor color;
    private final double weight;
    private final int cost;

    public Ball(BallColor color, double weight, int cost) {
        this.color = color;
        this.weight = weight;
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ball ball = (Ball) o;
        return Double.compare(ball.weight, weight) == 0 &&
                cost == ball.cost &&
                color == ball.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, weight, cost);
    }

    @Override
    public String toString() {
        return "Ball{" +
                "color=" + color +
                ", weight=" + weight +
                ", cost=" + cost +
                '}';
    }

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
