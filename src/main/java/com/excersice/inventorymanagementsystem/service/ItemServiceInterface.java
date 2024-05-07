package com.excersice.inventorymanagementsystem.service;

import com.excersice.inventorymanagementsystem.model.Item;

import java.util.List;

public interface ItemServiceInterface {

    Item addItem(Item item);

    Item updateItem(Item item);

    String deleteItem(Long id);

    Item getItem(Long id);

    List<Item> getAllItems();

    void deleteItemBy(Long itemId);

}
