package src.com.ilyabuglakov.arraymanipulator.service.validator;

import src.com.ilyabuglakov.arraymanipulator.repository.JuggedArray;

public class JuggedArrayValidator<T> {

    public boolean isRectangular(JuggedArray<T> matrix){
        int size = matrix.get(0).size();
        for(int i = 0;i<matrix.size();++i){
            if(matrix.get(i).size()!=size)
                return false;
        }
        return true;
    }

}
