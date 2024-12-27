import java.io.*;
import java.sql.SQLOutput;
import java.util.*;

public class Customer{
    protected String name;
    private Menu menu;
    private OrderManager orderManager;
    private List<Map<String, Object>> cart = new ArrayList<>();
    private static Map<Integer, List<String>> itemReviews = new HashMap<>();
    private Map<String, List<Order>> orderHistoryMap = new HashMap<>();

    public Customer(String name, OrderManager orderManager, Menu menu) {
        this.name=name;
        this.menu = menu;
        this.orderManager = orderManager;
        loadOrderHistory();
        loadCart();
    }


    public void customerInterface(Scanner scanner) {
        while (true) {
            System.out.println("\nCustomer Interface:");
            System.out.println("Enter 1 to Browse menu");
            System.out.println("Enter 2 to View cart");
            System.out.println("Enter 3 to Add item to cart");
            System.out.println("Enter 4 to Remove item from cart");
            System.out.println("Enter 5 to Place order");
            System.out.println("Enter 6 to Track order");
            System.out.println("Enter 7 to View order history");
            System.out.println("Enter 8 to for item review");
            System.out.println("Enter 0 to Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    menu.customerdisplayMenu();
                    break;
                case 2:
                    viewCart();
                    break;
                case 3:
                    addToCart(scanner);
                    break;
                case 4:
                    removeItem(scanner);
                    break;
                case 5:
                    orderManager.placeOrder(cart, this);
                    cart.clear();
                    break;
                case 6:
                    orderManager.trackOrder(scanner);
                    break;
                case 7:
                    displayOrderHistory(this);
                    break;
                case 8:
                    itemReview(scanner);
                    break;
                case 0:
                    System.out.println("Exiting from customer interface.");
                    System.out.println();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }



    public boolean addItemToCartById(int itemId) {
        Map<String, Object> menuItem = menu.getItemById(itemId);
        if (menuItem == null) {
            return false;
        }
        if (!(boolean) menuItem.get("available")) {
            return false;
        }

        Map<String, Object> cartItem = new HashMap<>(menuItem);
        cartItem.put("quantity", 1);
        cart.add(cartItem);
        saveCart();
        return true;
    }


    private void saveCart() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("cart_" + name + ".txt"))) {
            oos.writeObject(cart);
        }
        catch (IOException e) {
            System.out.println("Error saving cart: " + e.getMessage());
        }
    }


    private void loadCart() {
        File file = new File("cart_" + name + ".txt");
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                cart = (List<Map<String, Object>>) ois.readObject();
            }
            catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading cart: " + e.getMessage());
            }
        }
    }


    public void addToCart(Scanner sc) {
        displayMenu();
        System.out.print("Enter the item ID you want to add to the cart-> ");
        int itemId = sc.nextInt();
        sc.nextLine();
        Map<String, Object> menuItem = menu.getItemById(itemId);
        if (menuItem != null) {
            System.out.print("Enter the quantity: ");
            int quantity = sc.nextInt();
            sc.nextLine();
            Map<String, Object> cartItem = new HashMap<>(menuItem);
            cartItem.put("quantity", quantity);
            cart.add(cartItem);
            saveCart();  // Save the cart after adding an item
            System.out.println("Item added to cart successfully.");
        }
        else {
            System.out.println("Item not found in the menu.");
        }
    }


    public void removeItem(Scanner sc) {
        viewCart();
        System.out.print("Enter item ID you want to remove: ");
        int itemId = sc.nextInt();
        sc.nextLine();
        Map<String, Object> cartItem = getItemById(itemId);
        if (cart.remove(cartItem)) {
            saveCart();
            System.out.println("Item removed from cart.");
        }
        else {
            System.out.println("Item not found in the cart.");
        }
    }


    public void saveOrderHistory() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("order_history_" + name + ".txt"))) {
            oos.writeObject(orderHistoryMap);
        }
        catch (IOException e) {
            System.out.println("Error saving order history: " + e.getMessage());
        }
    }


    public void loadOrderHistory() {
        File file = new File("order_history_" + name + ".txt");
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                orderHistoryMap = (Map<String, List<Order>>) ois.readObject();
            }
            catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading order history: " + e.getMessage());
            }
        }
    }

    public String getName() {
        return name;
    }

    public List<Order> getOrders(Customer customer) {
        return orderHistoryMap.getOrDefault(customer.getName(), new ArrayList<>());
    }

    public boolean authenticate(String name) {
        if((this.name.equals(name))){
            return true;
        }
        else{
            return false;
        }
    }

    public void displayOrderHistory(Customer customer) {
        List<Order> orders = getOrders(customer);
        if (orders.isEmpty()) {
            System.out.println("No order history available.");
        }
        else {
            System.out.println("Order History for " + customer.getName() + ":");
            for (Order order:orders) {
                System.out.println(order);
            }
        }
    }


    public void itemReview(Scanner sc){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter 1 to give review.");
        System.out.println("Enter 2 to view review.");
        int option=scanner.nextInt();

        if(option==1){
            viewCart();

            System.out.print("Enter item id you want to review-> ");
            int itemId = scanner.nextInt();
            scanner.nextLine();


            Map<String, Object> item = getItemById(itemId);
            if (item == null) {
                System.out.println("Item not found in your orders.");
                return;
            }
            else{
                System.out.print("Enter your review for " + item.get("name") + "-> ");
                String review = scanner.nextLine();

                itemReviews.computeIfAbsent(itemId, k -> new ArrayList<>()).add(review);
                System.out.println("Thank you for your review!");
            }
        }
        else if(option==2){
            System.out.println("\nCanteen Menu:");
            if (menu.menuItems.isEmpty()) {
                System.out.println("No items in menu.");
            }
            else{
                for(Map<String, Object>item:menu.menuItems){
                    System.out.println(item.get("id") + ": " + item.get("name") + ", Rs:- " + item.get("price") + ", Category-> " + item.get("category") +
                            (item.get("available").equals(true)? " (Available)" : " (Unavailable)"));
                    String category=(String) item.get("category");
                }
            }

            System.out.print("Enter the ID of the item you want to view reviews for-> ");
            int itemId = scanner.nextInt();
            scanner.nextLine();

            List<String> reviews = itemReviews.get(itemId);
            if (reviews == null || reviews.isEmpty()) {
                System.out.println("No reviews available for this item.");
            } else {
                System.out.println("Reviews for item " + itemId + ":");
                for (String review : reviews) {
                    System.out.println(" - " + review);
                }
            }
        }
    }

    public void displayMenu() {
        System.out.println("\nCanteen Menu:");
        if (menu.menuItems.isEmpty()) {
            System.out.println("No items in the menu.");
        } else {
            for (Map<String, Object> item : menu.menuItems) {
                System.out.println(item.get("id") + ": " + item.get("name") + " - $" + item.get("price") + ", Category-> " + item.get("category") +
                        (item.get("available").equals(true) ? " (Available)" : " (Unavailable)"));
                String category = (String) item.get("category");
            }
        }
    }



    public Map<String, Object> getItemById(int id) {
        for (Map<String, Object> item : cart) {
            if ((int) item.get("id") == id) {
                return item;
            }
        }
        return null;
    }




    public void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("\nCart:");
            for (Map<String, Object> item : cart) {
                System.out.println(item.get("id") + ": " + item.get("name") + " - $" + item.get("price") +
                        ", Quantity: " + item.get("quantity") +
                        (item.get("available").equals(true) ? " (Available)" : " (Unavailable)"));
            }
        }

    }
}
