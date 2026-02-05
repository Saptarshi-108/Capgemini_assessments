class EmployeePayrollSystem {

    public static void main(String[] args) {
        try {
            Employee hr = new Employee(1, "Rita", "HR");
            Employee dev = new Employee(2, "Arun", "DEV");

            Payroll ft = new FullTimeEmployee(101, "Alice", 60000);
            Payroll ct = new ContractEmployee(102, "Bob", 1200, 20);

            ft.updateSalary(65000, hr);
            ct.updateSalary(1500, hr);

            System.out.println("Full-Time Pay: " + ft.calculatePay());
            System.out.println("Contract Pay: " + ct.calculatePay());

            ft.updateSalary(70000, dev);

        } catch (InvalidSalaryException e) {
            System.out.println("Checked Exception: " + e.getMessage());
        } catch (UnauthorizedAccessException e) {
            System.out.println("Runtime Exception: " + e.getMessage());
        }
    }
}

interface Payroll {
    double calculatePay();
    void updateSalary(double amount, Employee requester) throws InvalidSalaryException;
}

class Employee {

    private int empId;
    private String name;
    private String role;

    Employee(int id, String name, String role) {
        this.empId = id;
        this.name = name;
        this.role = role;
    }

    String getRole() {
        return role;
    }
}

abstract class BaseEmployee implements Payroll {

    private int employeeId;
    private String employeeName;
    private double salary;

    BaseEmployee(int id, String name, double sal) throws InvalidSalaryException {
        if (sal <= 0) {
            throw new InvalidSalaryException("Salary must be positive");
        }
        employeeId = id;
        employeeName = name;
        salary = sal;
    }

    protected double getSalary() {
        return salary;
    }

    protected void setSalary(double sal) throws InvalidSalaryException {
        if (sal <= 0) {
            throw new InvalidSalaryException("Invalid salary value");
        }
        salary = sal;
    }

    protected void authorize(Employee emp) {
        if (!"HR".equals(emp.getRole())) {
            throw new UnauthorizedAccessException("Only HR can modify salary");
        }
    }

    public void updateSalary(double amount, Employee requester) throws InvalidSalaryException {
        authorize(requester);
        setSalary(amount);
    }
}

class FullTimeEmployee extends BaseEmployee {

    FullTimeEmployee(int id, String name, double sal) throws InvalidSalaryException {
        super(id, name, sal);
    }

    public double calculatePay() {
        return getSalary();
    }
}

class ContractEmployee extends BaseEmployee {

    private int hoursWorked;

    ContractEmployee(int id, String name, double rate, int hours) throws InvalidSalaryException {
        super(id, name, rate);
        hoursWorked = hours;
    }

    public double calculatePay() {
        return getSalary() * hoursWorked;
    }
}

class UnauthorizedAccessException extends RuntimeException {
    UnauthorizedAccessException(String msg) {
        super(msg);
    }
}

class InvalidSalaryException extends Exception {
    InvalidSalaryException(String msg) {
        super(msg);
    }
}
