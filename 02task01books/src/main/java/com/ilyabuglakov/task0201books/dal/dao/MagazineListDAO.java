package com.ilyabuglakov.task0201books.dal.dao;

import com.ilyabuglakov.task0201books.dal.specification.Specification;
import com.ilyabuglakov.task0201books.exception.DaoAddException;
import com.ilyabuglakov.task0201books.exception.DaoRemoveException;
import com.ilyabuglakov.task0201books.exception.DaoWrongTypeException;
import com.ilyabuglakov.task0201books.model.magazine.Magazine;
import com.ilyabuglakov.task0201books.model.publication.Publication;
import com.ilyabuglakov.task0201books.storage.MagazineList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MagazineListDAO implements GenericDao<Magazine> {
    private MagazineList magazineList = new MagazineList();

    @Override
    public void clear() {
        magazineList.setMagazines(new ArrayList<>());
    }

    @Override
    public void add(Publication publication) throws DaoAddException {
        if (publication.getClass() != Magazine.class)
            throw new DaoAddException("Error while trying to add something not magazine to magazineList");
        Magazine magazine = (Magazine) publication;
        if (magazineList.getMagazines().contains(magazine))
            throw new DaoAddException("Magazine is already in MagazineList");
        magazineList.addMagazine(magazine);
    }

    @Override
    public Magazine get(int index) {
        return magazineList.getMagazines().get(index);
    }

    @Override
    public int indexOf(Publication publication) throws DaoWrongTypeException {
        if (publication.getClass() != Magazine.class)
            throw new DaoWrongTypeException("Error while trying to find something not Magazine in magazineList");
        return indexOf((Magazine) publication);
    }

    public int indexOf(Magazine o) {
        return magazineList.getMagazines().indexOf(o);
    }

    @Override
    public void remove(Publication publication) throws DaoRemoveException {
        if (publication.getClass() != Magazine.class)
            throw new DaoRemoveException("Error while trying to add something not magazine to magazineList");
        remove((Magazine) publication);
    }

    public void remove(Magazine existingObject) throws DaoRemoveException {
        if (!magazineList.removeMagazine(existingObject))
            throw new DaoRemoveException("Cant remove magazine from MagazineList");
    }

    @Override
    public List<Magazine> getAll() {
        return null;
    }

    @Override
    public void setAll(List<Magazine> content) {
        magazineList.setMagazines(content);
    }

    @Override
    public void set(int index, Publication publication) throws DaoWrongTypeException {
        if (publication.getClass() != Magazine.class)
            throw new DaoWrongTypeException("Error while trying to set something not magazine to magazineList");
        set(index, (Magazine) publication);
    }

    public void set(int index, Magazine newInstance) {
        magazineList.getMagazines().set(index, newInstance);
    }

    @Override
    public <S extends Specification<Magazine>> Optional<Magazine> findByCriteria(S criteria) {
        return Optional.empty();
    }

    @Override
    public <S extends Specification<Magazine>> List<Magazine> findAllByCriteria(S criteria) {
        return null;
    }

    @Override
    public void sortBy(Comparator<? super Magazine> comparator) {
        magazineList.setMagazines(magazineList.getMagazines().stream()
                .sorted(comparator)
                .collect(Collectors.toList()));
    }
}
