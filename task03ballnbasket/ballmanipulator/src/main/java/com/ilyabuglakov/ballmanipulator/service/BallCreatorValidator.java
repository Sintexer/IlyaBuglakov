package main.java.com.ilyabuglakov.ballmanipulator.service;

import main.java.com.ilyabuglakov.ballmanipulator.model.ball.BallColor;

import java.math.BigDecimal;
import java.util.Objects;

public class BallCreatorValidator {

    public boolean isValidWeight(BigDecimal weight){
        return weight.compareTo(BigDecimal.ZERO) >0;
    }

    public boolean isValidCost(BigDecimal cost){
        return cost.compareTo(BigDecimal.ZERO) >0;
    }

    public boolean isNull(Object o){
        return Objects.isNull(  o);
    }

    public boolean areValidArgs(BigDecimal weight, BigDecimal cost, BallColor color){
        if(isNull(weight) || isNull(cost) || isNull(color))
            return false;
        return isValidWeight(weight) && isValidCost(cost);
    }

}
