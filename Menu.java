import java.util.*;

public class Menu {
    List<Map<String, Object>> menuItems = new ArrayList<>();
    private List<Map<String, Object>> beverage = new ArrayList<>();
    private List<Map<String, Object>> snacks = new ArrayList<>();
    private List<Map<String, Object>> meals = new ArrayList<>();
    private int nextId = 1;

    public Menu() {
        this.menuItems = new ArrayList<>();
    }


    public void customerdisplayMenu() {
        beverage.clear();
        meals.clear();
        snacks.clear();

        System.out.println("\nCanteen Menu:");
        if (menuItems.isEmpty()) {
            System.out.println("No items in menu.");
        }
        else{
            for(Map<String, Object>item:menuItems){
                System.out.println(item.get("id") + ": " + item.get("name") + ", Rs:- " + item.get("price") + ", Category-> " + item.get("category") +
                        (item.get("available").equals(true)? " (Available)" : " (Unavailable)"));
                String category=(String) item.get("category");

                if(category.equalsIgnoreCase("beverage")){
                    beverage.add(item);
                }
                else if (category.equalsIgnoreCase("snacks")) {
                    snacks.add(item);
                }
                else if (category.equalsIgnoreCase("meals")) {
                    meals.add(item);
                }
            }
        }

        System.out.println();
        System.out.println("Enter 1 to filter by category. ");
        System.out.println("Enter 2 to sort by price.");

        Scanner sc=new Scanner(System.in);
        int option=sc.nextInt();

        if(option==1){
            System.out.println("Beverages:");
            for (Map<String, Object> bevitem : beverage) {
                System.out.println(bevitem.get("id") + ": " + bevitem.get("name") + ", Rs:-" + bevitem.get("price") + ", Category-> " + bevitem.get("category") +
                        (bevitem.get("available").equals(true) ? " (Available)" : " (Unavailable)"));
            }
            System.out.println();

            System.out.println("Snacks:");
            for (Map<String, Object> snackitem : snacks) {
                System.out.println(snackitem.get("id") + ": " + snackitem.get("name") + ", Rs:-" + snackitem.get("price") + ", Category-> " + snackitem.get("category") +
                        (snackitem.get("available").equals(true) ? " (Available)" : " (Unavailable)"));
            }
            System.out.println();

            System.out.println("Meals:");
            for (Map<String, Object> mealitem : meals) {
                System.out.println(mealitem.get("id") + ": " + mealitem.get("name") + ", Rs:-" + mealitem.get("price") + ", Category-> " + mealitem.get("category") +
                        (mealitem.get("available").equals(true) ? " (Available)" : " (Unavailable)"));
            }
            System.out.println();
        }
        else if(option==2){
            System.out.println("Enter 1 to sort in ascending order of prices.");
            System.out.println("Enter 2 to sort in descending order of prices.");
            int asc=sc.nextInt();

            if(asc==1){
                menuItems.sort(Comparator.comparingDouble(item -> (double) item.get("price")));

                for (Map<String, Object> item : menuItems) {
                    System.out.print(item.get("id") + ": " + item.get("name") + ", Rs:-" + item.get("price") + ", Category-> " + item.get("category"));
                    if ((boolean) item.get("available")) {
                        System.out.println(", (Available)");
                    } else {
                        System.out.println(", (Unavailable)");
                    }
                }
            }
            else if(asc==2){
                menuItems.sort((item1, item2) -> Double.compare((double) item2.get("price"), (double) item1.get("price")));
                for (Map<String, Object> item : menuItems) {
                    System.out.print(item.get("id") + ": " + item.get("name") + ", Rs:-" + item.get("price") + ", Category-> " + item.get("category"));
                    if ((boolean) item.get("available")) {
                        System.out.println(", (Available)");
                    } else {
                        System.out.println(", (Unavailable)");
                    }
                }
            }
        }
    }

    public List<Map<String, Object>> displayMenu() {
        return menuItems;
    }


    public Map<String, Object> getItemById(int id) {
        for (Map<String, Object> item:menuItems) {
            if((int) item.get("id")==id){
                return item;
            }
        }
        return null;
    }

    public List<Map<String, Object>> admindisplayMenu(){
        System.out.println("\nCanteen Menu:");
        if (menuItems.isEmpty()) {
            System.out.println("No items in menu.");
        }
        else{
            for(Map<String, Object> item:menuItems){
                System.out.println(item.get("id") + ": " + item.get("name") + ", Rs:- " + item.get("price") + ", Category-> " + item.get("category") + (item.get("available").equals(true)? " (Available)" : " (Unavailable)"));
                String category=(String) item.get("category");
            }
        }

        System.out.println();
        return null;
    }

    public void updateItem(Scanner sc) {
        System.out.println("\nCanteen Menu:");
        if (menuItems.isEmpty()) {
            System.out.println("No items in menu.");
        }
        else{
            for(Map<String, Object>item:menuItems){
                System.out.println(item.get("id") + ": " + item.get("name") + ", Rs:- " + item.get("price") + ", Category-> " + item.get("category") +
                        (item.get("available").equals(true)? " (Available)" : " (Unavailable)"));
                String category=(String) item.get("category");
            }
        }

        System.out.println();

        System.out.print("Enter item id to update-> ");
        int itemId = sc.nextInt();
        sc.nextLine();

        for(Map<String, Object> item:menuItems){
            if((int) item.get("id")==itemId){
                System.out.print("Enter new item name-> ");
                String name = sc.nextLine();
                item.put("name", name);

                System.out.print("Enter price-> ");
                double price = sc.nextDouble();
                sc.nextLine();
                item.put("price", price);
            }
            else{
                System.out.println("Item not found in menu.");
            }
        }
        System.out.println("Item updated successfully.");
    }



    public void removeItem(Scanner sc){
        System.out.println("\nCanteen Menu:");
        if (menuItems.isEmpty()) {
            System.out.println("No items in menu.");
        }
        else{
            for(Map<String, Object>item:menuItems){
                System.out.println(item.get("id") + ": " + item.get("name") + ", Rs:- " + item.get("price") + ", Category-> " + item.get("category") +
                        (item.get("available").equals(true)? " (Available)" : " (Unavailable)"));
                String category=(String) item.get("category");
            }
        }

        System.out.println();

        System.out.print("Enter the item id to remove-> ");
        int itemId = sc.nextInt();
        sc.nextLine();


        for(Map<String, Object> item:menuItems){
            if((int) item.get("id")==itemId){
                menuItems.remove(item);
                System.out.println("Item removed successfully.");
            }
            else{
                System.out.println("Item not found in menu.");
            }
        }
    }



    public void modifyPrice(Scanner sc) {
        System.out.println("\nCanteen Menu:");
        if (menuItems.isEmpty()) {
            System.out.println("No items in menu.");
        }
        else{
            for(Map<String, Object>item:menuItems){
                System.out.println(item.get("id") + ": " + item.get("name") + ", Rs:- " + item.get("price") + ", Category-> " + item.get("category") +
                        (item.get("available").equals(true)? " (Available)" : " (Unavailable)"));
                String category=(String) item.get("category");
            }
        }

        System.out.println();

        System.out.print("Enter the item id to modify price->");
        int itemId = sc.nextInt();
        sc.nextLine();


        for(Map<String, Object> item:menuItems){
            if((int) item.get("id")==itemId){
                System.out.print("Enter new price-> ");
                double price = sc.nextDouble();
                sc.nextLine();
                item.put("price", price);

                System.out.println("Price updated successfully.");
            }
            else{
                System.out.println("Item not found in menu.");
            }
        }
    }



    public void updateAvailability(Scanner sc) {
        displayMenu();

        System.out.print("Enter the item id to update availability-> ");
        int itemId = sc.nextInt();
        sc.nextLine();

        for(Map<String, Object> item:menuItems){
            if((int) item.get("id")==itemId){
                System.out.print("Update the item availability to-> ");
                boolean availability = sc.nextBoolean();
                sc.nextLine();

                item.put("available", availability);
                System.out.println("Item updated successfully.");
            }
            else{
                System.out.println("Item not found in menu.");
            }
        }
    }


    public void addItem(Scanner sc) {
        System.out.print("Enter item name-> ");
        String name = sc.nextLine();
        System.out.print("Enter price-> ");
        double price = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter category (beverage, snacks, meals)-> ");
        String category=sc.nextLine();

        Map<String, Object> item = new HashMap<>();
        item.put("id", nextId++);
        item.put("name", name);
        item.put("price", price);
        item.put("available", true);
        item.put("category", category);

        menuItems.add(item);
        System.out.println("Item added successfully.");
    }


    public List<Map<String, Object>> getMenuItems() {
        return menuItems;
    }
}
