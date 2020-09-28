package main.java.com.ilyabuglakov.ballmanipulator.controller;

import main.java.com.ilyabuglakov.ballmanipulator.model.ball.Ball;
import main.java.com.ilyabuglakov.ballmanipulator.repository.Basket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BasketController {

    private List<Basket> baskets = new ArrayList<>();
    ViewController viewController = new ViewController(this);

    public void run() {
        viewController.operateMainMenu();
    }

    public List<Basket> getBaskets(){
        return baskets;
    }

}
