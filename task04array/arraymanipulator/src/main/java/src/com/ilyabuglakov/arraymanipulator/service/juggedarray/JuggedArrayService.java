package src.com.ilyabuglakov.arraymanipulator.service.juggedarray;

import src.com.ilyabuglakov.arraymanipulator.repository.Array;
import src.com.ilyabuglakov.arraymanipulator.repository.JuggedArray;
import src.com.ilyabuglakov.arraymanipulator.service.array.ArrayCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntBinaryOperator;

public class JuggedArrayService<T> {

    public boolean isSquareMatrix(JuggedArray<T> jarray) {
        int size = jarray.size();
        for (var arr : jarray.getContent()) {
            if (size != arr.size())
                return false;
        }
        return true;
    }


    public JuggedArray<Integer> modifyValues(JuggedArray<Integer> array, Integer val, IntBinaryOperator operator) {
        JuggedArrayCreator<Integer> creator = new JuggedArrayCreator<>();
        ArrayCreator<Integer> arrayCreator = new ArrayCreator<>();
        JuggedArray<Integer> result = creator.createJuggedArray(array.size());
        for (int i = 0; i < result.size(); ++i) {
            result.setRow(i, arrayCreator.createArray(array.get(i).size()));
            for (int j = 0; j < array.get(i).size(); ++j) {
                Integer newVal = operator.applyAsInt(array.get(i).get(j), val);
                result.get(i).set(j, newVal);
            }
        }
        return result;
    }

    public boolean sameSize(JuggedArray<T> arr1, JuggedArray<T> arr2) {
        if (arr1.size() != arr2.size())
            return false;
        Array<Array<T>> rows1 = arr1.getContent();
        Array<Array<T>> rows2 = arr2.getContent();
        for (int i = 0; i < arr1.size(); ++i) {
            if (rows1.get(i).size() != rows2.get(i).size())
                return false;
        }
        return true;
    }

    public List<T> getColumn(JuggedArray<T> matrix, int columnId){
        List<T> column = new ArrayList<>(matrix.size());
        for(int i = 0;i<matrix.size();++i){
            column.add(matrix.get(i).get(columnId));
        }
        return column;
    }

    public void swapRows(JuggedArray<T> matrix ,int row1, int row2){
        Array<T> temp = matrix.get(row1);
        matrix.setRow(row1, matrix.get(row2));
        matrix.setRow(row2, temp);
    }

}
