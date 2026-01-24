## 1. Secure Banking Transaction Engine

### Problem Statement
A fintech company is building a Secure Banking Transaction Engine for internal audits and simulations.  
The system must manage different bank account types while ensuring data safety, validation, authorization, and robust exception handling.

You are required to design and implement this system using core Java object-oriented principles.

---

### Requirements

#### 1. Core Design
- Treat class, abstract class, and interface as templates appropriately.
- Create a base abstraction for a Bank Account.

#### 2. Bank Account Details
Each bank account must contain:
- Account Number  
- Account Holder Name  
- Balance  

#### 3. Encapsulation Rules
- All variables must be private.
- Balance must never be directly accessible or modifiable.
- Access to balance must be provided only through controlled getters and setters.

#### 4. Validation and Authorization
Apply the following rules inside setters or service methods:
- Balance cannot be negative.
- Withdrawal beyond available balance must be prevented.
- Invalid transaction amounts must be rejected.

#### 5. Account Types
Implement at least:
- Savings Account
- Current Account  

Account behavior must differ using runtime polymorphism.

#### 6. Polymorphism and Overloading
- Overload methods for:
  - Deposit (cash, online)
  - Withdraw (with and without remarks)
- Override transaction behavior where required.

#### 7. Exception Handling
- Custom checked exception:
  - InvalidAmountException
- Custom unchecked exception:
  - InsufficientBalanceException

Demonstrate:
- try-catch-finally
- throw and throws
- Exception propagation

---

### Constraints
- No public variables allowed.
- No switch-case for account behavior.
- All logic must be written in a single Java file.
- Proper object-oriented design is mandatory.

---

### Deliverable
- Submit a single .java file implementing the complete system.

---
---



## 2. Employee Access and Payroll System

### Problem Statement
A company requires a secure employee payroll system where sensitive data must be protected, and access should be strictly controlled based on roles.

The system must be designed using encapsulation, abstraction, polymorphism, and exception handling.

---

### Requirements

#### 1. Employee Model
Each employee must have:
- Employee ID  
- Name  
- Role  
- Salary  

#### 2. Encapsulation
- Salary must be private.
- Direct access to salary is prohibited.
- Salary updates must go through controlled setters.

#### 3. Authorization Rules
- Only HR role is allowed to modify salary.
- Unauthorized access must be blocked.

#### 4. Abstraction
- Use an interface to define payroll behavior.
- Implement payroll differently for:
  - Full-time employees
  - Contract employees

#### 5. Polymorphism
- Use method overriding for payroll calculation.
- Use method overloading where applicable.

#### 6. Exception Handling
- Custom unchecked exception:
  - UnauthorizedAccessException
- Custom checked exception:
  - InvalidSalaryException

Demonstrate:
- Exception propagation
- Proper exception handling

---

### Constraints
- No public variables.
- No duplicate logic.
- No misuse of static methods.
- Entire solution must be written in a single Java file.

---

### Deliverable
- Submit a single .java file implementing the payroll system.
