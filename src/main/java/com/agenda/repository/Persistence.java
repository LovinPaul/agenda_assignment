package com.agenda.repository;

import com.agenda.model.Item;

import java.util.List;

public interface Persistence {

    void saveObject(Object o);
    void updateObject(Object o);
    void deleteObject(Object o);
    Item getItem(Long item);
    List<Item> getItems();

}
