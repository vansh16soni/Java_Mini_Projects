# Java Mini Projects

A collection of Java mini projects, object-oriented programming (OOP) implementations, design patterns, and practical console applications.

---

## 📂 Projects Overview

### 1. 📚 Library Management System (`miniProject/library`)

An extensible, console-based Library Management System demonstrating core **Object-Oriented Programming (OOP)** principles, interface design, polymorphic fine calculations, member tracking, and file persistence in Java.

#### 🏗️ System Architecture & OOP Design

- **Interface Abstraction (`Loanable`):**
  - Defines the contract for loan periods (`getMaxLoanDays()`) and polymorphic overdue fine calculations (`calculateFine(int daysOverdue)`).
- **Abstract Base Class (`LibraryItem`):**
  - Implements `Loanable`.
  - Encapsulates common item attributes (`id`, `title`, `isIssued`) with safe state transitions (`issue()`, `returnItem()`).
  - Enforces `getItemType()` implementation for all subclasses.
- **Specialized Item Classes:**
  - `Book`: 14-day loan period, flat overdue fine (₹5/day).
  - `Magazine`: 3-day short-term loan period, priority overdue fine (₹10/day).
  - `AudioBook`: 7-day loan period, base fine (₹2/day) with extra penalty for >5 days overdue.
- **Member Management (`Patron`):**
  - Tracks member information (`memberId`, `name`) and manages borrowed items with a capacity constraint (max 3 items).
- **System Controller & Operations (`Library`):**
  - Demonstrates composition by managing item catalogs and patron directories.
  - Handles item checkout, return with automated overdue fine calculation, and lost item fee assessments.
- **Data Persistence (`FileManager`):**
  - Persists and loads catalog state to/from disk (`catalog.txt`) using `BufferedReader` and `BufferedWriter` with try-with-resources.
- **Interactive CLI (`LibraryApp`):**
  - Menu-driven console interface for browsing catalog inventory, checking out items, returning items, and case-insensitive keyword searching.

---

#### 📁 Project Structure

```
miniProject/library/
├── AudioBook.java       # AudioBook subclass with duration & penalty rules
├── Book.java            # Book subclass with author & loan rules
├── FileManager.java     # File I/O for saving and loading catalog data
├── Library.java         # Library controller managing transactions & patrons
├── LibraryApp.java      # Interactive CLI application & entry point
├── LibraryItem.java     # Abstract base class implementing Loanable
├── Loanable.java        # Interface for loan rules & fine calculations
├── Magazine.java        # Magazine subclass with issue number & short-term loan rules
└── Patron.java          # Member entity tracking borrowed items & limits
```

---

#### 🚀 How to Run

From the root project directory:

```bash
# 1. Compile all Java source files
javac miniProject/library/*.java

# 2. Run the Library Application
java miniProject.library.LibraryApp
```

---

## 🛠️ Requirements
- **Java Development Kit (JDK):** 8 or higher
- **IDE (Optional):** IntelliJ IDEA, Eclipse, or VS Code

---

## 👤 Author
- **GitHub:** [@vansh16soni](https://github.com/vansh16soni)
