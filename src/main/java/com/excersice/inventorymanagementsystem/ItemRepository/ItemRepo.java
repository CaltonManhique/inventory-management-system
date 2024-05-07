package com.excersice.inventorymanagementsystem.ItemRepository;

import com.excersice.inventorymanagementsystem.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item, Long> {
}
