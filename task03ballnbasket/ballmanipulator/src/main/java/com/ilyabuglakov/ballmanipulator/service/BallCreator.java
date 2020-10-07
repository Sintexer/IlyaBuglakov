package main.java.com.ilyabuglakov.ballmanipulator.service;

import main.java.com.ilyabuglakov.ballmanipulator.model.ball.Ball;
import main.java.com.ilyabuglakov.ballmanipulator.model.ball.BallColor;

import java.math.BigDecimal;

public class BallCreator {

    public Ball createBall(BigDecimal weight, BigDecimal cost, BallColor color){
        return new Ball(color, weight, cost);
    }

}
