package N1.EX1.repository;

import N1.EX1.models.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientRepository {

    public void save(Client client) {
        String sql = "INSERT INTO client (name,address,phone,email,created,recommended_by_id,employee)" +
                     "VALUES (?,?,?,?,?,?,?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getAddress());
            preparedStatement.setInt(3, client.getPhone());
            preparedStatement.setString(4, client.getEmail());
            preparedStatement.setObject(5, client.getCreated());
            preparedStatement.setObject(6, client.getRecommendedBy());
            preparedStatement.setString(7,client.getEmployee());

            preparedStatement.execute();
            System.out.println("Save successful");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }


    }
}
