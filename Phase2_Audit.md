# Phase 2: Codebase Audit Report

This document outlines the findings from the deep audit of the repository. Issues are categorized by priority (P0, P1, P2) and include evidence, impact, fix approaches, and validation plans.

## P0: Critical Issues (Must Fix Now)

### 1. Missing Build System & Testing Framework

- **Evidence:** Terminal execution failed during Phase 1. No `pom.xml`, `build.gradle`, or `test/` directory.
- **Impact:** Without a build tool (Maven/Gradle), managing dependencies, running tests, and preparing for production deployment is manual and error-prone. The lack of JUnit means no automated verification of core business logic.
- **Fix Approach:**
  1. Introduce Maven (`pom.xml`).
  2. Implement standard project layout (`src/main/java` and `src/test/java`).
  3. Add `junit-jupiter` dependencies.
- **Validation Plan:** `mvn clean test` runs and passes successfully.

### 2. Invalid File Names & Class Definitions (DX & Consistency)

- **Evidence:** `Class.java` defines `class Payment`. `LIneItem.java` has a casing typo.
- **Impact:** In Java, top-level public classes must match their file names. This mismatch violates Java standards and confuses developers, potentially breaking compilation under stricter settings.
- **Fix Approach:** Rename `Class.java` to `Payment.java` and `LIneItem.java` to `LineItem.java`.
- **Validation Plan:** The compiler successfully finds and compiles these types.

---

## P1: Significant Issues (Logic Correctness & Architecture)

### 3. Missing Input Validation (Logic Correctness & Security)

- **Evidence:** `LineItem.setQuantity(int)` and `LineItem.setUnitPrice(BigDecimal)` accept negative values. `Invoice` accepts null clients.
- **Impact:** An invoice could compute negative totals, leading to incorrect real-world billing and corrupted data states.
- **Fix Approach:** Implement guard clauses in setters. Throw `IllegalArgumentException` for invalid domains (e.g., `quantity <= 0`).
- **Validation Plan:** Write unit tests that expect `IllegalArgumentException` when passing negative quantities or prices.

### 4. Legacy Date Handling (Reliability)

- **Evidence:** `Invoice.java` uses `java.util.Date` and manipulates time via millisecond arithmetic (`new Date(System.currentTimeMillis() + 7L * 24 * 60 * 60 * 1000)`).
- **Impact:** `java.util.Date` is mutable and lacks timezone safety. Millisecond math is error-prone and ignores leap seconds or daylight saving time.
- **Fix Approach:** Migrate all date fields to Java 8 Time API (`java.time.LocalDate` or `LocalDateTime`). Use `LocalDate.now().plusDays(7)` for clear semantics.
- **Validation Plan:** Ensure unit tests accurately assert date boundaries using the `java.time` API.

### 5. Single Responsibility Principle Violation (Architecture)

- **Evidence:** `Invoice.java` contains a `main` method that hardcodes objects and acts as the application entry point.
- **Impact:** Domain models should not be responsible for application lifecycle, UI generation, or bootstrapping.
- **Fix Approach:** Extract the `main` method into a separate dedicated class (e.g., `Application.java` or `Main.java`) acting as the service/app layer.
- **Validation Plan:** The application can be executed from the new `Main` class, and `Invoice` only acts as a pure POJO.

### 6. Missing Package Structure & Encapsulation

- **Evidence:** All files sit in the root `src/` directory with package-private class definitions (e.g., `class Client`).
- **Impact:** Bad organizational practice at scale. It leaks abstractions due to lack of standard visibility controls.
- **Fix Approach:** Move classes into a domain package (e.g., `com.invoice.domain`) and an application package (`com.invoice.app`). Make models `public`.
- **Validation Plan:** Maven compilation succeeds with the new package structure.

---

## P2: Minor Issues (Tech Debt)

### 7. Overly Simplistic Password Handling

- **Evidence:** `User.java` has `String password; // encrypted`.
- **Impact:** While mock apps use plain strings, a production app requires a clear distinction. Storing a raw "encrypted" string in a domain model without a handler is risky.
- **Fix Approach:** Either remove this from the domain if it's unused in the current flow, or annotate/document that the string represents a BCrypt hash, renaming to `passwordHash`.
- **Validation Plan:** Ensure no plain text logging can occur via `toString()` overrides (exclude password field).
