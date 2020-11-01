package com.ilyabuglakov.task0201books.dal.specification.publication;

import com.ilyabuglakov.task0201books.model.publication.Publication;

public class FirstLetterMatchSpecification implements PublicationSpecification {

    private char letter;

    public void setLetter(char letter) {
        this.letter = letter;
    }

    @Override
    public boolean isSatisfiedBy(Publication criteria) {
        return letter == criteria.getName().charAt(0);
    }
}
