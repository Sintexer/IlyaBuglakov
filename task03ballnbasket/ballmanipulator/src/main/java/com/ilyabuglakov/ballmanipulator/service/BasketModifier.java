package main.java.com.ilyabuglakov.ballmanipulator.service;

import main.java.com.ilyabuglakov.ballmanipulator.model.ball.Ball;
import main.java.com.ilyabuglakov.ballmanipulator.repository.Basket;

import java.util.List;

public class BasketModifier {

    private BasketCreator creator = new BasketCreator();

    public Basket addBall(Basket basket, Ball ball){
        List<Ball> balls = basket.getBalls();
        balls.add(ball);
        return creator.createBasket(balls);
    }

    public Basket removeBall(Basket basket, Ball ball){
        List<Ball> balls = basket.getBalls();
        balls.remove(ball);
        return creator.createBasket(balls);
    }

    public Basket removeBall(Basket basket, int index){
        List<Ball> balls = basket.getBalls();
        balls.remove(index);
        return creator.createBasket(balls);
    }

}
