package com.excersice.inventorymanagementsystem.controller;

import com.excersice.inventorymanagementsystem.model.Item;
import com.excersice.inventorymanagementsystem.service.ItemServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/inventory")
public class ItemController {

    private ItemServiceInterface itemService;

    @Autowired
    public ItemController(ItemServiceInterface itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/add")
    public Item addItem(@RequestBody Item item) {
        item.setItemId(0L);
        return itemService.addItem(item);
    }

    @PutMapping("/update")
    public Item updateItem(@RequestBody Item item) {
        return itemService.updateItem(item);
    }

    @GetMapping("/items")
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteItem(@PathVariable Long id) {
        return itemService.deleteItem(id);
    }

    @GetMapping("/item/{id}")
    public Item getItem(@PathVariable Long id) {
        return itemService.getItem(id);
    }
}
