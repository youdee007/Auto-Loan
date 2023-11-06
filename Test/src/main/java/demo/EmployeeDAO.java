package demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
	
	private Connection connection ;

    public EmployeeDAO(String url, String username, String password) {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:7070/Aubaid", "postgres","aubaid");
        } catch (SQLException e) {
        	//System.out.println("Conecction is failed");
            e.printStackTrace();
        }
    }

    public void addEmployee(Employee employee) {
        try {
            String sql = "INSERT INTO employees (name, age) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setInt(2, employee.getAge());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                employees.add(new Employee(id, name, age));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public void updateEmployee(Employee employee) {
        try {
            String sql = "UPDATE employees SET name = ?, age = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setInt(2, employee.getAge());
            preparedStatement.setInt(3, employee.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployee(int id) {
        try {
            String sql = "DELETE FROM employees WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (connection != null) {
            	System.out.println("Connection is Ok");
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}