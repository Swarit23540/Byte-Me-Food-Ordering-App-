import java.util.List;
import java.util.Map;

public class Order {
    private int orderId;
    private Customer customer;
    private List<Map<String, Object>> foodItems;
    private double totalPrice;
    private String status;
    private String request;
    private String VIP;

    public Order(int orderId, Customer customer, String status, List<Map<String, Object>> foodItems, String request, String VIP) {
        this.orderId = orderId;
        this.customer = customer;
        this.foodItems = foodItems;
        this.status = status;
        this.totalPrice = calculateTotalPrice();
        this.request = request;
        this.VIP = VIP;
    }

    public String getSpecialRequest() {
        return request;
    }

    private double calculateTotalPrice() {
        double total = 0.0;
        for (Map<String, Object> item : foodItems) {
            total += (double) item.get("price");
        }
        return total;
    }

    public int getOrderId() {
        return orderId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Map<String, Object>> getFoodItems() {
        return foodItems;
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId + ", Customer: " + customer.getName() + ", Total Price: $" + totalPrice + ", Status: " + status + ", Special Request: " + getSpecialRequest() + ", VIP Status: " + VIP;
    }

    public String getVipStatus() {
        return VIP;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice=totalPrice;
    }
}
