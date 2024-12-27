import java.util.Scanner;

public class ByteMeSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();
        OrderManager orderManager = new OrderManager();
        Customer customer1 = new Customer("Swarit", orderManager, menu);
        Customer customer2= new Customer("Naman", orderManager, menu);
        Customer customer3 = new Customer("Rahul", orderManager, menu);

        Admin admin = new Admin("Admin", orderManager,menu);


        while (true) {
            System.out.println("Welcome to Byte Me! Food Ordering System");
            System.out.println("Enter 1 to login as Admin.");
            System.out.println("Enter 2 to login as Customer.");
            System.out.println("Enter 3 for GUI.");
            System.out.println("Enter 0 to Close the System.");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                admin.adminInterface(scanner);
            }
            else if (choice == 2) {
                System.out.print("Enter student name-> ");
                String name = scanner.nextLine();



                Customer currentCustomer = null;
                if (customer1.authenticate(name)) {
                    currentCustomer = customer1;
                } else if (customer2.authenticate(name)) {
                    currentCustomer = customer2;
                } else if (customer3.authenticate(name)) {
                    currentCustomer = customer3;
                }
                if (currentCustomer != null) {
                    System.out.println(currentCustomer.getName() + " logged in successfully.");
                    currentCustomer.customerInterface(scanner);
                }
                else{
                    System.out.println("Invalid login attempt.");
                }
            }
            else if (choice == 3) {
                ByteMeGUI gui = new ByteMeGUI(orderManager, customer3, admin, menu);
            }
            else if (choice == 0) {
                break;
            }
        }
    }
}