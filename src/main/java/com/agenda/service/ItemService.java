package com.agenda.service;

import com.agenda.model.Item;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ItemService {

    List<Item> getLastItems();
    Item getItem(Long id);

    void saveOrUpdateItem(Item item);

    void deleteItem(Long id);

    Item getItemFromRequest(HttpServletRequest request);
}
