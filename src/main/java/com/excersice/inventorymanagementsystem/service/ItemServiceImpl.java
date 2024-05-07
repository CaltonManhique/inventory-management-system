package com.excersice.inventorymanagementsystem.service;

import com.excersice.inventorymanagementsystem.ItemRepository.ItemRepo;
import com.excersice.inventorymanagementsystem.model.Item;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemServiceInterface {

    private ItemRepo itemRepo;

    @Autowired
    public ItemServiceImpl(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    @Override
    @Transactional
    public Item addItem(Item item) {
        return itemRepo.save(item);
    }

    @Override
    @Transactional
    public Item updateItem(Item item) {
        return itemRepo.save(item);
    }

    @Override
    @Transactional
    public String deleteItem(Long id) {
        String sucessMessage = "";

        Optional<Item> item = itemRepo.findById(id);
        if (item.isPresent()) {
            itemRepo.delete(item.get());
            sucessMessage = "Successfully deleted Item with id: " + id;
        } else {
            throw new RuntimeException("Item not Found");
        }
        return sucessMessage;
    }

    @Override
    public Item getItem(Long id) {
        Item item = null;

        Optional<Item> itemOptional = itemRepo.findById(id);
        if (itemOptional.isPresent()) {
            item = itemOptional.get();
        } else {
            throw new RuntimeException("Item not found!");
        }
        return item;
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepo.findAll();
    }

    // Simple version
    @Override
    public void deleteItemBy(Long itemId) {
        itemRepo.deleteById(itemId);
    }
}
