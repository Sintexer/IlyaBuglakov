package com.ilyabuglakov.task0201books.storage;

import com.ilyabuglakov.task0201books.model.magazine.Magazine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Stores Magazine objects.
 */
public class MagazineList {
    private List<Magazine> magazines;

    public MagazineList(Collection<Magazine> magazines) {
        this.magazines = new ArrayList<>(magazines);
    }

    public MagazineList() {
        magazines = new ArrayList<>();
    }

    public void addMagazine(Magazine magazine) {
        magazines.add(magazine);
    }

    public boolean removeMagazine(Magazine magazine) {
        return magazines.remove(magazine);
    }

    public void setMagazines(List<Magazine> magazines) {
        this.magazines = magazines;
    }

    public List<Magazine> getMagazines() {
        return new ArrayList<>(magazines);
    }
}
