package com.ilyabuglakov.raise.domain;

import com.ilyabuglakov.raise.domain.type.Characteristic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Test extends Entity {
    private String testName;
    private int difficulty;
    private List<Characteristic> characteristics; //TODO should be saved in another table
    private Set<Question> questions;

    public static int BASE_DIFFICULTY = 1;


    @Override
    public String toString() {
        return "Test{" +
                "testName='" + testName + '\'' +
                ", difficulty=" + difficulty +
                ", characteristics=" + characteristics +
                ", questions=" + questions +
                '}';
    }
}//TODO add dao method getCharacteristics

