package src.com.ilyabuglakov.arraymanipulator.service.juggedarray;

import src.com.ilyabuglakov.arraymanipulator.repository.Array;
import src.com.ilyabuglakov.arraymanipulator.repository.JuggedArray;

import java.util.Comparator;

public class JuggedArraySorter<T> {

    public JuggedArray<T> sortBubble(JuggedArray<T> matrix, Comparator<Array<T>> rowComparator){
        JuggedArrayService<T> service = new JuggedArrayService<>();
        JuggedArray<T> result = new JuggedArrayCreator<T>().createJuggedArray(matrix);
        boolean swapped = true;
        while(swapped){
            swapped = false;
            for (int i = 1;i<result.size();++i){
                if(rowComparator.compare(result.get(i), result.get(i-1))<0) {
                    service.swapRows(result, i, i - 1);
                    swapped = true;
                }
            }
        }
        return result;
    }

}
