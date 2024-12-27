import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Map;

public class ByteMeGUI {
    private JFrame frame;
    private JTextArea textArea;
    private Admin admin;
    private Customer customer;
    private Menu menu;
    private OrderManager orderManager;

    public ByteMeGUI(OrderManager orderManager, Customer customer, Admin admin, Menu menu) {
        this.orderManager = orderManager;
        this.customer = customer;
        this.admin = admin;
        this.menu=menu;
        initialize();
    }

    public void initialize() {
        frame = new JFrame("Byte Me!");
        frame.setBounds(100, 100, 800, 600);
        frame.setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setBackground(Color.pink);
        textArea.setFont(new Font("Monospaced", Font.BOLD, 17));

        textArea.setEditable(false);
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);


        JPanel panel = new JPanel();
        panel.setBackground(Color.darkGray);
        panel.setPreferredSize(new Dimension(800, 50));
        panel.setLayout(new FlowLayout());

        JButton menuButton = new JButton("View Menu");
        JButton pendingOrdersButton = new JButton("View Pending Orders");

        menuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayMenu();
            }
        });

        pendingOrdersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayPendingOrders();
            }
        });

        panel.add(menuButton);
        panel.add(pendingOrdersButton);
        frame.add(panel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }



    private void displayMenu() {
        textArea.setText("");
        List<Map<String, Object>> menuItems = menu.getMenuItems();

        textArea.append("CANTEEN MENU: \n");
        textArea.append("\n");
        if (menuItems.isEmpty()) {
            textArea.append("No items in menu.");
        }
        else{
            for (Map<String, Object> item : menuItems) {
                textArea.append("Item->"+item.get("name") + ", Rs-" + item.get("price") + ", "+ (item.get("available").equals(true)? " (Available)" : " (Unavailable)")+ "\n");
            }
        }
    }

    private void displayPendingOrders() {
        textArea.setText("");
        List<Order> pendingOrders = orderManager.getPendingOrders();
        textArea.append("PENDING ORDERS: \n");
        for (Order order : pendingOrders) {
            textArea.append(order.toString() + "\n");
        }
    }
}

