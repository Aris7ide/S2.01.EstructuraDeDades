package N1.EX1.repository;

import N1.EX1.models.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {

    public Employee save(Connection connection, Employee employee) {
        String sql = "INSERT INTO employee (firstName, lastName, phone, date)" +
                    "VALUES (?,?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getPhone());
            preparedStatement.setObject(4, employee.getCreated());

            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    employee.setId(generatedKeys.getInt(1));
                }
            }

            System.out.println("Employee saved successful with id: " + employee.getId());
            return findById(connection, employee.getId());

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public Employee findById(Connection connection, int id) {
        String sql = "SELECT * FROM employee WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Employee employee = new Employee(
                            rs.getString("firstName"),
                            rs.getString("lastName"),
                            rs.getString("phone")
                    );

                    employee.setId(id);

                    return employee;
                }
            }
        } catch  (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

}
