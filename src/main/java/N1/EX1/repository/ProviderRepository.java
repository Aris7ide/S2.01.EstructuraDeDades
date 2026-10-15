package N1.EX1.repository;

import N1.EX1.models.Provider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ProviderRepository {

    public void save(Provider provider) {
        String sql = "INSERT INTO provider (name,street,number, floor, door, city, postal_code, country, phone, fax, nif)" +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, provider.getName());
            preparedStatement.setString(2, provider.getStreet());
            preparedStatement.setString(3, provider.getNumber());
            preparedStatement.setString(4, provider.getFloor());
            preparedStatement.setString(5, provider.getDoor());
            preparedStatement.setString(6, provider.getCity());
            preparedStatement.setString(7, provider.getPostalCode());
            preparedStatement.setString(8, provider.getCountry());
            preparedStatement.setString(9, provider.getPhone());
            preparedStatement.setString(10, provider.getFax());
            preparedStatement.setString(11, provider.getNif());

            preparedStatement.executeUpdate();
            System.out.println("Saved provider");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
