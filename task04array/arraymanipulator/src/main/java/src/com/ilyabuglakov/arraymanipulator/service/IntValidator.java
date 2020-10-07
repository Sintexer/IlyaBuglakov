package src.com.ilyabuglakov.arraymanipulator.service;

public class IntValidator {

    public boolean isInBounds(int val, int leftBound, int rightBound) {
        return val >= leftBound && val < rightBound;
    }

    public boolean isValidSize(int val){
        return isInBounds(val, 1, Integer.MAX_VALUE);
    }

}
