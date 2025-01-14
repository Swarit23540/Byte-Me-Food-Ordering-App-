# Byte Me - Food Ordering System 🍽️  

**Byte Me** is a CLI-based food ordering system designed for a college canteen. It provides a simple, user-friendly interface for customers to order food and for admins to manage the menu and track orders.  

---

![1735298522488](https://github.com/user-attachments/assets/da7d87c1-6377-4bf3-b323-6c8c2e4b0092)

## Features 🚀  

### Customer Features:  
- Browse the menu with category and price filtering.  
- Place orders with **special requests** (e.g., "extra spicy" or "no onions").  
- View order status (pending/completed).  

### Admin Features:  
- Add, update, or remove menu items.  
- View and manage pending orders.  
- Generate daily sales reports for revenue tracking.  

### Additional Features:  
- VIP order priority management.  
- Option to switch between **CLI and GUI**.  
- JUnit tests implemented for critical components.  

---

## Classes and Responsibilities 📂  

- **Main:** Initializes the system and provides the main application interface.  
- **Customer:** Handles customer-specific functionalities like browsing the menu and placing orders.  
- **Admin:** Manages admin functionalities like menu updates and sales reports.  
- **Menu:** Manages menu item storage, updates, and retrieval.  
- **Order:** Represents individual customer orders, including special requests and VIP status.  
- **OrderManager:** Handles order tracking, order status changes, and reporting.  

---

## Installation & Setup 🛠️  

1. **Clone the Repository**:  
   ```bash  
   git clone https://github.com/your-username/byte-me.git  
   cd byte-me  
