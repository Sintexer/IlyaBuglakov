package com.ilyabuglakov.task0201books.dal.repository;

import com.ilyabuglakov.task0201books.dal.dao.BookListDao;
import com.ilyabuglakov.task0201books.dal.dao.GenericDao;
import com.ilyabuglakov.task0201books.dal.dao.MagazineListDAO;
import com.ilyabuglakov.task0201books.dal.specification.book.BookSpecification;
import com.ilyabuglakov.task0201books.dal.specification.magazine.MagazineSpecification;
import com.ilyabuglakov.task0201books.exception.DaoAddException;
import com.ilyabuglakov.task0201books.exception.DaoRemoveException;
import com.ilyabuglakov.task0201books.exception.DaoWrongTypeException;
import com.ilyabuglakov.task0201books.model.book.Book;
import com.ilyabuglakov.task0201books.model.book.BookComparator;
import com.ilyabuglakov.task0201books.model.magazine.Magazine;
import com.ilyabuglakov.task0201books.model.publication.Publication;
import com.ilyabuglakov.task0201books.model.publication.PublicationComparator;
import com.ilyabuglakov.task0201books.service.IdGenerator;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class PublicationRepository {
    private static PublicationRepository instance = new PublicationRepository();
    private Map<Class<? extends Publication>, GenericDao<? extends Publication>> daosMap = new HashMap<>();

    private PublicationRepository() {
        daosMap.put(Book.class, new BookListDao());
        daosMap.put(Magazine.class, new MagazineListDAO());
    }

    public static PublicationRepository getInstance() {
        return instance;
    }

    public long add(Publication publication) throws DaoAddException {
        long id = IdGenerator.getInstance().next();
        publication.setId(id);
        daosMap.get(publication.getClass()).add(publication);
        return id;
    }

    public Optional<? extends Publication> get(long id){
        return daosMap.values().stream()
                .map(GenericDao::getAll)
                .flatMap(l -> l.stream())
                .filter(publ -> publ.getId() == id)
                .findFirst();
    }

    public void remove(Publication publication) throws DaoRemoveException {
        daosMap.get(publication.getClass()).remove(publication);
    }

    public boolean remove(long id) throws DaoRemoveException {
        Optional<? extends Publication> p = get(id);
        if (p.isPresent()) {
            Publication old = p.get();
            daosMap.get(old.getClass()).remove(p.get());
            return true;
        }
        return false;
    }

    public void update(long id, Publication publication) throws DaoWrongTypeException {
        Optional<? extends Publication> p = get(id);
        if (p.isPresent()) {
            Publication old = p.get();
            int index = daosMap.get(old.getClass()).indexOf(old);
            daosMap.get(old.getClass()).set(index, p.get());
        }
    }

    public List<Publication> getAll(){
        return daosMap.values().stream()
                .map(GenericDao::getAll)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public List<Publication> getAllSorted() {
        return daosMap.values().stream()
                .map(GenericDao::getAll)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .sorted()
                .collect(Collectors.toList());
    }

    public List<Publication> getAllSorted(Comparator<Publication> comparator) {
        return daosMap.values().stream()
                .map(GenericDao::getAll)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    public Optional<Book> find(BookSpecification specification) {
        return ((BookListDao) daosMap.get(Book.class)).findByCriteria(specification);
    }

    public Optional<Magazine> find(MagazineSpecification specification) {
        return ((MagazineListDAO) daosMap.get(Magazine.class)).findByCriteria(specification);
    }

    public List<Book> findAll(BookSpecification specification) {
        return ((BookListDao) daosMap.get(Book.class)).findAllByCriteria(specification);
    }

    public List<Magazine> findAll(MagazineSpecification specification) {
        return ((MagazineListDAO) daosMap.get(Magazine.class)).findAllByCriteria(specification);
    }

    public void sort(BookComparator comparator) {
        ((BookListDao) daosMap.get(Book.class)).sortBy(comparator);
    }

    public void sort(PublicationComparator comparator) {
        ((MagazineListDAO) daosMap.get(Magazine.class)).sortBy(comparator);
    }

    public void clear(){
        for(Class<? extends Publication> key:  daosMap.keySet()){
            daosMap.get(key).clear();
        }
    }
//TODO Tests
}

