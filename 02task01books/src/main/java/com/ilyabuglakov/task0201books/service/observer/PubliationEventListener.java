package com.ilyabuglakov.task0201books.service.observer;

import com.ilyabuglakov.task0201books.model.publication.Publication;

public interface PubliationEventListener {
    void publicationAdded(Publication publications);

    void publicationRemoved(Publication publications);

    void publicationUpdated(Publication old, Publication updated);
}
