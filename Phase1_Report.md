# Phase 1: Internalization and Baseline Report

## Part A & B: System Map & Flows

### 1. Repo Inventory

- **Stack:** Java SE (Core Java)
- **Architecture:** Simple single-package Object-Oriented structure.
- **Build Tools / Package Manager:** None present. Code is intended to be compiled via `javac` directly or within an IDE.
- **Directory Structure:**
  - `d:\Invoice\README.md`
  - `d:\Invoice\INVOICE\src\` contains the Java source code.
- **Key Domain Models:**
  - `Class.java` (Contains `Payment` class)
  - `Client.java` (Represents clients)
  - `Invoice.java` (Core invoice logic, contains the `main` method entry point)
  - `LIneItem.java` (Line items for an invoice)
  - `Report.java` (Reporting logic)
  - `User.java` (System user)
- **Data Layers / Storage:** None. Data is hardcoded in memory (within `Invoice.main()`).
- **External Integrations:** None.
- **Configuration & Env Vars:** None.

### 2. Flow Mapping

- **Entry Point:** `Invoice.main(String[] args)`
- **Core User Journey:**
  1.  The `main` method initializes a `Client` object with hardcoded data.
  2.  It creates several `LineItem` objects with descriptions, quantities, and prices.
  3.  It aggregates them into an `Invoice` object, setting tax, discount, and statuses.
  4.  It prints the generated invoice out to the console using `System.out.println`.
- **Payment Lifecycle:** Unimplemented aside from the `Payment` class existing as a data model. The `main` method simply prints "Unpaid".
- **Security / Permissions boundary:** The `User` class has a `role` and `permissions` list, but it is completely unused in the current execution flow. There is no real authentication.

---

## Part C: Baseline Health Report

### Execution Commands Attempted

```powershell
cd d:\Invoice\INVOICE\src
javac *.java
```

### Results

- **Status:** **FAILED**
- **Log Summary:**
  ```text
  javac: The term 'javac' is not recognized as a name of a cmdlet, function, script file, or executable program.
  ```
- **Analysis:** The development environment (Windows PATH) is not currently configured with the Java Development Kit (JDK), preventing baseline compilation from the terminal.

### Pre-Audit Observations (Phase 2 Preview)

- `Class.java` contains the `class Payment`. This is a naming violation (`Class.java` does not match `Payment`).
- `LIneItem.java` has a typo in its filename.
- The `README.md` states "Rename `Class.java` to `Main.java`" and "Run `java Class`", which is incorrect, as the `main` method actually resides in `Invoice.java`.
- No automated tests (JUnit) exist.
- No standard build system (Maven/Gradle) is used.
