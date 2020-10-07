package main.java.com.ilyabuglakov.ballmanipulator.service;

import main.java.com.ilyabuglakov.ballmanipulator.model.ball.Ball;
import main.java.com.ilyabuglakov.ballmanipulator.repository.Basket;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BasketCreator {

    public Basket createBasket(List<Ball> balls){
        return Basket.of(balls);
    }

    public Basket createEmptyBasket(){
        return Basket.of(Collections.emptyList());
    }

}
