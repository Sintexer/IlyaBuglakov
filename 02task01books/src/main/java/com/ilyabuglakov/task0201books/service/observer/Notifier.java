package com.ilyabuglakov.task0201books.service.observer;

import com.ilyabuglakov.task0201books.model.publication.Publication;

public interface Notifier {
    void addEventListener(PubliationEventListener eventListener);

    void removeEventListener(PubliationEventListener eventListener);

    void notifyAllAdd(Publication publication);

    void notifyAllRemove(Publication publication);

    void notifyAllUpdate(Publication old, Publication updated);
}
