import java.util.*;
import java.io.*;

public class OrderManager {
    private List<Order> pendingOrders = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private int nextOrderId = 1;

    public void placeOrder(List<Map<String, Object>> cart, Customer customer) {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty. Add items before placing an order.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter address-> ");
        String address = sc.nextLine();

        System.out.print("Any special request? (yes or no)-> ");
        String yn = sc.nextLine();


        Order order;

        if (yn.equalsIgnoreCase("yes")) {
            System.out.print("What is your special request-> ");
            String req = sc.nextLine();
            System.out.print("Want to apply for VIP customer? (yes or no)-> ");
            String vip = sc.nextLine();

            if (vip.equalsIgnoreCase("yes")) {
                order = new Order(nextOrderId++, customer, "Pending", cart, req, "VIP");
            } else {
                order = new Order(nextOrderId++, customer, "Pending", cart, req, "NON-VIP");
            }
        }
        else {
            System.out.print("Want to apply for VIP customer? (yes or no)-> ");
            String vip = sc.nextLine();

            if (vip.equalsIgnoreCase("yes")) {
                order = new Order(nextOrderId++, customer, "Pending", cart, "No special request", "VIP");
            } else {
                order = new Order(nextOrderId++, customer, "Pending", cart, "No special request", "NON-VIP");
            }
        }


        orders.add(order);
        pendingOrders.add(order);
        customer.saveOrderHistory();


        System.out.println("Order placed successfully! Your order ID is: " + order.getOrderId() + ", with total amount: Rs-" + order.getTotalPrice());
    }



    public void trackOrder(Scanner scanner) {
        System.out.println("Enter 1 to view order status.");
        System.out.println("Enter 2 to cancel order.");
        int option = scanner.nextInt();
        if (option == 1) {
            System.out.print("Enter your order ID to track-> ");
            int orderId = scanner.nextInt();
            scanner.nextLine();
            for (Order order : pendingOrders) {
                if (order.getOrderId() == orderId) {
                    System.out.println("Order ID: " + orderId + ", Total Amount: $" + order.getTotalPrice() + ", Status: " + order.getStatus());
                    return;
                }
            }
            System.out.println("Order not found.");
        } else if (option == 2) {
            System.out.println("Enter your order ID to cancel:");
            int orderId = scanner.nextInt();
            scanner.nextLine();
            for (Order order : pendingOrders) {
                if (order.getOrderId() == orderId) {
                    order.setStatus("Cancelled");
                    pendingOrders.remove(order);
                    System.out.println("Order ID: " + orderId + " has been cancelled successfully.");
                    break;
                }
            }
        }
    }



    public void processRefund() {
        boolean refundProcessed = false;
        for (Order order : orders) {
            if ("Cancelled".equalsIgnoreCase(order.getStatus())) {
                orders.remove(order);
                System.out.println("Refund processed for cancelled order ID: " + order.getOrderId());
                refundProcessed = true;
            }
        }
        if (!refundProcessed) {
            System.out.println("No cancelled orders found to process refunds.");
        }
    }


    public void generateDailySalesReport() {
        double totalSales = 0;
        for (Order order : pendingOrders) {
            if ("Delivered".equals(order.getStatus())) {
                totalSales += order.getTotalPrice();
            }
        }
        System.out.println("Total Sales: $" + totalSales);
    }

    public List<Order> getPendingOrders() {
        return pendingOrders;
    }


}

