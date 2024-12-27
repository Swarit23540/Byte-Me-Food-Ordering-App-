import java.io.*;
import java.util.*;

public class Admin {
    private Menu menu;
    protected String name;
    private List<Order> orders = new ArrayList<>();
    private List<Map<String, Object>> menuItems = new ArrayList<>();
    private OrderManager orderManager;
    private int nextId = 1;

    public Admin(String name, OrderManager orderManager, Menu menu) {
        this.name = name;
        this.orderManager = orderManager;
        this.menu = menu;
    }


    public void adminInterface(Scanner scanner) {
        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("Press 1 for View menu");
            System.out.println("Press 2 for Add item to menu");
            System.out.println("Press 3 for Update item in menu");
            System.out.println("Press 4 for Remove item from menu");
            System.out.println("Press 5 for Modify item price");
            System.out.println("Press 6 for Update Availability");
            System.out.println("Press 7 for View pending orders");
            System.out.println("Press 8 for Update order status");
            System.out.println("Press 9 for Process refunds");
            System.out.println("Press 10 for Generate daily sales report");
            System.out.println("Press 0 for Return to login page");
            System.out.print("Enter your choice: ");


            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    menu.admindisplayMenu();
                    break;
                case 2:
                    menu.addItem(scanner);
                    break;
                case 3:
                    menu.updateItem(scanner);
                    break;
                case 4:
                    menu.removeItem(scanner);
                    break;
                case 5:
                    menu.modifyPrice(scanner);
                    break;
                case 6:
                    menu.updateAvailability(scanner);
                    break;
                case 7:
                    viewPendingOrders();
                    break;
                case 8:
                    updateOrderStatus(scanner);
                    break;
                case 9:
                    orderManager.processRefund();
                    break;
                case 10:
                    orderManager.generateDailySalesReport();
                    break;
                case 0:
                    System.out.println("Logging out...");
                    System.out.println();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }




    private void updateOrderStatus(Scanner scanner) {
        System.out.print("Enter order ID to update->");
        int orderId = scanner.nextInt();
        scanner.nextLine();


        List<Order> pendingOrders = orderManager.getPendingOrders();
        for (Order order : pendingOrders) {
            if (order.getOrderId() == orderId) {
                System.out.print("Enter new status (Pending, Preparing, Delivered, Cancelled): ");
                String status = scanner.nextLine();
                order.setStatus(status);
                System.out.println("Order status updated successfully.");
                return;
            }
        }
        System.out.println("Order not found.");
    }


    private void viewPendingOrders() {
        System.out.println("\nPending Orders:");
        List<Order> pendingOrders = orderManager.getPendingOrders();

        if (pendingOrders.isEmpty()) {
            System.out.println("There is no pending orders.");
        }
        else {
            for (Order order : pendingOrders) {
                System.out.println(order);
            }
        }
    }
}


