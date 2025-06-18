# Java Invoice Management System

A lightweight, object-oriented invoice management application built in Java. This project demonstrates class design, encapsulation, and simple I/O-based reporting for managing clients, users, and billing line items.

---

## 🧠 Key Skills Demonstrated

- **Object-Oriented Programming (OOP):** Encapsulation, modular class design (`User`, `Client`, `Invoice`, etc.)
- **File and Report Generation:** Structured invoice reporting with `Report.java`
- **Data Modeling:** Clear separation of responsibilities through specialized classes
- **Code Readability & Maintainability:** Logical organization and straightforward method structure
- **Practical Use Case Implementation:** Simulates a real-world business process (client billing)

---

## 🧱 Tech Stack

- **Language:** Java SE
- **Structure:** Single-package class-based architecture
- **Run Environment:** Terminal or IDE (Eclipse, IntelliJ)
- **Input Format:** Hardcoded or test-structured objects
- **Output:** Invoice reports via console or file

---

## 🗂️ Project Structure

INVOICE/
├── src/
│ ├── User.java → Represents a system user or employee
│ ├── Client.java → Represents a client/customer
│ ├── Invoice.java → Core invoice details (client, items, totals)
│ ├── LIneItem.java → Billing line items (description, price, quantity)
│ ├── Report.java → Report generator
│ └── Class.java → Main entry point (can be renamed to Main.java)



---

## 🚀 How to Run

### Option 1: In an IDE
1. Open the `INVOICE/src/` folder in Eclipse or IntelliJ
2. Rename `Class.java` to `Main.java` (recommended for clarity)
3. Run the `Main.java` file

### Option 2: From Terminal

```bash
cd INVOICE/src
javac *.java
java Class
