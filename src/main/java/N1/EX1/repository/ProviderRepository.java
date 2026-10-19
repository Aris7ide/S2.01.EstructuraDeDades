package N1.EX1.repository;

import N1.EX1.models.Provider;

import java.sql.*;

public class ProviderRepository {

    public void save(Connection connection, Provider provider) {
        String sql = "INSERT INTO provider (name,street,number, floor, door, city, postal_code, country, phone, fax, nif)" +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

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

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);

                    provider.setId(generatedId);

                    System.out.println("Proveedor guardado con éxito. ID asignado: " + generatedId);
                }
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
