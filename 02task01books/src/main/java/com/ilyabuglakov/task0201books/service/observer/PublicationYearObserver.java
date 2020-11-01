package com.ilyabuglakov.task0201books.service.observer;

import com.ilyabuglakov.task0201books.model.publication.Publication;
import org.apache.log4j.Logger;

import java.time.Year;
import java.util.HashMap;
import java.util.Map;

public class PublicationYearObserver implements PubliationEventListener {

    private Map<Year, Long> publicationYearAmount = new HashMap<>();
    Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void publicationAdded(Publication publication) {
        publicationYearAmount.merge(publication.getYearOfPublishing(), 1L, (old, newVal) -> old + newVal);
        logger.debug(publicationYearAmount);
    }

    @Override
    public void publicationRemoved(Publication publication) {
        publicationYearAmount.merge(publication.getYearOfPublishing(), 1L, (old, newVal) -> old - newVal);
        logger.debug(publicationYearAmount);
    }

    @Override
    public void publicationUpdated(Publication oldPubl, Publication updated) {
        publicationYearAmount.merge(oldPubl.getYearOfPublishing(), 1L, (old, newVal) -> old - newVal);
        publicationYearAmount.merge(updated.getYearOfPublishing(), 1L, (old, newVal) -> old + newVal);
        logger.debug(publicationYearAmount);
    }
}
