package N1.EX1.repository;

import N1.EX1.models.Client;

import java.sql.*;

public class ClientRepository {

    public Client save(Connection connection, Client client) {
        String sql = "INSERT INTO client (name,address,phone,email,created,recommended_by_id,employee)" +
                     "VALUES (?,?,?,?,?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getAddress());
            preparedStatement.setInt(3, client.getPhone());
            preparedStatement.setString(4, client.getEmail());
            preparedStatement.setObject(5, client.getCreated());
            preparedStatement.setString(7,client.getEmployee());

            if (client.getRecommendedBy() != null) {
                preparedStatement.setInt(6, client.getRecommendedBy().getId());
            } else {
                preparedStatement.setNull(6, java.sql.Types.INTEGER);
            }

            preparedStatement.execute();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    client.setId(generatedKeys.getInt(1));
                }
            }

            System.out.println("Save successful");
            return findById(connection, client.getId());

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public Client findById(Connection connection, int id) {
        String sql = "SELECT * FROM client WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Client client = new Client(
                            rs.getString("name"),
                            rs.getString("address"),
                            rs.getInt("phone"),
                            rs.getString("email"),
                            null,
                            rs.getString("employee")
                    );

                    client.setId(rs.getInt("id"));

                    return client;
                }
            }  catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }
}
