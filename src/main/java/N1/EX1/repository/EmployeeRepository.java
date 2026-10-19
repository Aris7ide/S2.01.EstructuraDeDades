package N1.EX1.repository;

import N1.EX1.models.Employee;

import java.sql.*;

public class EmployeeRepository {

    public void save(Connection connection, Employee employee) {
        String sql = "INSERT INTO employee (firstName, lastName, phone, created)" +
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

            System.out.println("Save successful");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
