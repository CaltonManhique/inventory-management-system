package com.excersice.inventorymanagementsystem;

import com.excersice.inventorymanagementsystem.ItemRepository.ItemRepo;
import com.excersice.inventorymanagementsystem.model.Item;
import com.excersice.inventorymanagementsystem.service.ItemServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ItemServiceImplTest {

    @Mock
    private ItemRepo itemRepo;

    @InjectMocks
    private ItemServiceImpl itemService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddItem() {
        Item item = new Item("Banana", "From Colombia", 1.99, 200);

        when(itemRepo.save(item)).thenReturn(item);

        Item result = itemService.addItem(item);

        assertNotNull(result);
        assertEquals("Banana", result.getItemName());
        assertEquals("From Colombia", result.getItemDescription());
        assertEquals(1.99, result.getItemPrice());
        assertEquals(200, result.getItemQuantity());
    }


    @Test
    public void testDeleteItem() {

        Item item = new Item("Banana", "From Colombia", 1.99, 200);
        item.setItemId(1L);

        when(itemRepo.findById(1L)).thenReturn(Optional.of(item));

        String result = itemService.deleteItem(1L);

        assertNotNull(result);
        assertEquals("Successfully deleted Item with id: 1", result);

        // For verification
        verify(itemRepo, times(1)).findById(1L);

        verify(itemRepo, times(1)).delete(item);

        verify(itemRepo, never()).deleteById(1L);

    }

    @Test
    public void testGetItemByExistingId() {
        Item item = new Item("Banana", "From Colombia", 1.99, 200);
        item.setItemId(1L);

        when(itemRepo.findById(1L)).thenReturn(Optional.of(item));

        Item result = itemService.getItem(1L);

        assertNotNull(result);
        assertEquals("Banana", result.getItemName());
        assertEquals("From Colombia", result.getItemDescription());
        assertEquals(1.99, result.getItemPrice());
        assertEquals(200, result.getItemQuantity());
    }

    @Test
    public void testGetItemByNonExistingId() {

        when(itemRepo.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> itemService.getItem(1L));

        assertEquals("Item not found!", exception.getMessage());

        verify(itemRepo, times(1)).findById(1L);
    }

    @Test
    public void testGetAllItems() {

        List<Item> items = Arrays.asList(new Item("Banana", "From Colombia", 1.99, 200),
                new Item("Orange", "From Portugal", 2.59, 300));

        when(itemRepo.findAll()).thenReturn(items);

        List<Item> result = itemService.getAllItems();

        assertEquals(2, result.size());
    }

    // Simple version
    @Test
    public void testDeleteItemById() {

        itemService.deleteItemBy(1L);
        verify(itemRepo, times(1)).deleteById(1L);
    }
}
