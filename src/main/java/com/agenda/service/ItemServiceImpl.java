package com.agenda.service;

import com.agenda.model.Item;
import com.agenda.repository.MySQLData;
import com.agenda.repository.Persistence;
import com.agenda.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.agenda.util.StringUtils.isEmpty;

public class ItemServiceImpl implements ItemService {

    private Persistence persistence = MySQLData.GetINSTACE();

    public ItemServiceImpl() {
    }

    public ItemServiceImpl(Persistence persistence) {
        this.persistence = persistence;
    }

    @Override
    public List<Item> getLastItems() {
        return persistence.getItems();
    }

    @Override
    public Item getItem(Long id) {
        return persistence.getItem(id);
    }

    @Override
    public void saveOrUpdateItem(Item item) {
        if (item == null) {
            return;
        }

        if (item.getId() == null) {
            saveItem(item);
        }

        updateItem(item);
    }

    private void saveItem(Item item) {
        persistence.saveObject(item);
    }

    private void updateItem(Item item) {
        persistence.updateObject(item);
    }

    @Override
    public void deleteItem(Long id) {
        Item item = persistence.getItem(id);

        if (item == null) {
            throw new RuntimeException(String.format("Item with id %d not found.", id));
        }

        persistence.deleteObject(item);
    }

    @Override
    public Item getItemFromRequest(HttpServletRequest request) {

        Long id = null;
        String parameterItemId = request.getParameter("item_id");
        if (!StringUtils.isEmpty(parameterItemId)) {
            id = Long.valueOf(parameterItemId);
        }

        String nume = request.getParameter("nume");
        String prenume = request.getParameter("prenume");
        String email = request.getParameter("email");
        String numarDeTelefon = request.getParameter("numar_telefon");
        String tara = request.getParameter("tara");

        if (isEmpty(nume) || isEmpty(prenume) || isEmpty(numarDeTelefon)) {
            throw new RuntimeException("One or more required fields are not valid [nume, prenume and/or numarDeTelefon]");
        }

        return new Item(id, nume, prenume, email, numarDeTelefon, tara);
    }

}
