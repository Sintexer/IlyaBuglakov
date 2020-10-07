package src.com.ilyabuglakov.arraymanipulator.service;

import src.com.ilyabuglakov.arraymanipulator.repository.JuggedArray;

public class JuggedArrayCreator<T> {
    //TODO singleton

    public JuggedArray<T> createJuggedArray(T[][] array) {
        ArrayCreator<T> arrayCreator = new ArrayCreator<>();
        JuggedArray<T> juggedArray = new JuggedArray<>(array.length);
        for (int i = 0; i < juggedArray.size(); ++i) {
            juggedArray.setRow(i, arrayCreator.createArray(array[i]));
        }
        return juggedArray;
    }

    public JuggedArray<T> createJuggedArray(JuggedArray<T> matrix){
        ArrayCreator<T> arrayCreator = new ArrayCreator<>();
        JuggedArray<T> juggedArray = new JuggedArray<>(matrix.size());
        for(int i = 0;i<juggedArray.size();++i){
            juggedArray.setRow(i, arrayCreator.createArray(matrix.get(i)));
        }
        return juggedArray;
    }

    public JuggedArray<T> createJuggedArray(int size) {
        return new JuggedArray<T>(size);
    }

}
