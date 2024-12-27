import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class OutOfStock {
    private Customer customer;
    private OrderManager orderManager;
    private Menu menu;

    @BeforeEach
    void setUp() {
        orderManager = new OrderManager();
        menu = new Menu();

        // Add a mock out-of-stock item
        Map<String, Object> outOfStockItem = new HashMap<>();
        outOfStockItem.put("id", 1);
        outOfStockItem.put("name", "Burger");
        outOfStockItem.put("price", 5.0);
        outOfStockItem.put("available", false);
        menu.menuItems.add(outOfStockItem);

        customer = new Customer("TestUser", orderManager, menu);
    }

    @Test
    void testAddOutOfStockItem() {
        // Attempt to add the out-of-stock item
        boolean added = customer.addItemToCartById(1);
        assertFalse(added, "The system should prevent adding out-of-stock items.");
    }
}
