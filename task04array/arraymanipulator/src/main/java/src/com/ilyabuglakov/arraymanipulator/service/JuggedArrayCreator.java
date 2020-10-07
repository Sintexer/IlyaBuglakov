package src.com.ilyabuglakov.arraymanipulator.service;

import src.com.ilyabuglakov.arraymanipulator.repository.JuggedArray;

public class JuggedArrayCreator<T> {

    public JuggedArray<T> createJuggedArray(T[][] array){
        ArrayCreator<T> arrayCreator = new ArrayCreator<>();
        JuggedArray<T> juggedArray = new JuggedArray<>(array.length);
        for(int i = 0; i<juggedArray.size();++i){
            juggedArray.setRow(i, arrayCreator.createArray(array[i]));
        }
        return juggedArray;
    }

}
