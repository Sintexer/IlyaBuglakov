package src.com.ilyabuglakov.arraymanipulator.service.juggedarray;

import src.com.ilyabuglakov.arraymanipulator.repository.JuggedArray;
import src.com.ilyabuglakov.arraymanipulator.service.array.ArrayCreator;
import src.com.ilyabuglakov.arraymanipulator.service.juggedarray.JuggedArrayCreator;
import src.com.ilyabuglakov.arraymanipulator.service.juggedarray.JuggedArrayService;

public class MatrixTransposer<T> {

    public JuggedArray<T> transpose(JuggedArray<T> matrix){
        JuggedArrayService<T> service = new JuggedArrayService<>();
        JuggedArrayCreator<T> creator = new JuggedArrayCreator<>();
        ArrayCreator<T> arrayCreator = new ArrayCreator<>();
        JuggedArray<T> result = creator.createJuggedArray(matrix.get(0).size());
        for(int i = 0;i<matrix.get(0).size();++i){
            result.setRow(i, arrayCreator.createArray(service.getColumn(matrix, i)));
        }
        return result;
    }

}
