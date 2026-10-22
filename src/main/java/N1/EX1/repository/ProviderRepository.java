package N1.EX1.repository;

import N1.EX1.models.Provider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public Provider findProviderById(Connection connection, int id) {
        String sql = "SELECT * FROM provider WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs  = ps.executeQuery()) {
                if (rs.next()) {
                    Provider provider = new Provider(
                            rs.getString("name"),
                            rs.getString("street"),
                            rs.getString("number"),
                            rs.getString("floor"),
                            rs.getString("door"),
                            rs.getString("city"),
                            rs.getString("postal_code"),
                            rs.getString("country"),
                            rs.getString("phone"),
                            rs.getString("fax"),
                            rs.getString("nif")
                    );

                    provider.setId(id);

                    return provider;
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }


        return null;
    }

    public List<Provider> findProvidersThatSold(Connection connection) {
        List<Provider> saleList = new ArrayList<>();
        String sql = "SELECT DISTINCT p.name " +
                "FROM provider p " +
                "JOIN glasses g ON p.id = g.provider_id " +
                "JOIN sales s ON g.id = s.glasses_id ";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Provider provider = new Provider();
                    provider.setName(resultSet.getString("name"));
                    saleList.add(provider);
                }
            }

        } catch (SQLException e ) {
            System.err.println(e.getMessage());
        }

        return saleList;
    }
}
