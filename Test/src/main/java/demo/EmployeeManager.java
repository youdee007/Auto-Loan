package demo;

import java.util.List;
import java.util.Scanner;

public class EmployeeManager {
	 private EmployeeDAO employeeDAO;

	    public EmployeeManager(EmployeeDAO employeeDAO) {
	        this.employeeDAO = employeeDAO;
	    }

	    public void addEmployee() {
	        Scanner scanner = new Scanner(System.in);
	        System.out.print("Enter employee name: ");
	        String name = scanner.nextLine();
	        System.out.print("Enter employee age: ");
	        int age = scanner.nextInt();

	        Employee employee = new Employee(0, name, age);
	        employeeDAO.addEmployee(employee);
	        System.out.println("Employee added successfully.");
	    }

	    public void listEmployees() {
	        List<Employee> employees = employeeDAO.getAllEmployees();
	        if (employees.isEmpty()) {
	            System.out.println("No employees found.");
	        } else {
	            System.out.println("List of employees:");
	            for (Employee employee : employees) {
	                System.out.println("ID: " + employee.getId() + ", Name: " + employee.getName() + ", Age: " + employee.getAge());
	            }
	        }
	    }

	    public void updateEmployee() {
	        listEmployees();
	        Scanner scanner = new Scanner(System.in);
	        System.out.print("Enter the ID of the employee to update: ");
	        int id = scanner.nextInt();

	        List<Employee> employees = employeeDAO.getAllEmployees();
	        Employee employeeToUpdate = null;
	        for (Employee employee : employees) {
	            if (employee.getId() == id) {
	                employeeToUpdate = employee;
	                break;
	            }
	        }

	        if (employeeToUpdate == null) {
	            System.out.println("Employee with ID " + id + " not found.");
	        } else {
	            System.out.print("Enter new name: ");
	            scanner.nextLine();
	            String name = scanner.nextLine();
	            System.out.print("Enter new age: ");
	            int age = scanner.nextInt();

	            employeeToUpdate = new Employee(id, name, age);
	            employeeDAO.updateEmployee(employeeToUpdate);
	            System.out.println("Employee updated successfully.");
	        }
	    }

	    public void deleteEmployee() {
	        listEmployees();
	        Scanner scanner = new Scanner(System.in);
	        System.out.print("Enter the ID of the employee to delete: ");
	        int id = scanner.nextInt();

	        List<Employee> employees = employeeDAO.getAllEmployees();
	        Employee employeeToDelete = null;
	        for (Employee employee : employees) {
	            if (employee.getId() == id) {
	                employeeToDelete = employee;
	                break;
	            }
	        }

	        if (employeeToDelete == null) {
	            System.out.println("Employee with ID " + id + " not found.");
	        } else {
	            employeeDAO.deleteEmployee(id);
	            System.out.println("Employee deleted successfully.");
	        }
	    }

}
