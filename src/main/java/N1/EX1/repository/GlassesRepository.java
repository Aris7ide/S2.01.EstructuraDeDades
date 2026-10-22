package N1.EX1.repository;

import N1.EX1.models.Glasses;
import N1.EX1.models.Provider;

import java.sql.*;

public class GlassesRepository {

    public Glasses save(Connection connection, Glasses glasses) {
        String sql = "INSERT INTO glasses (brand,left_graduation,right_graduation,frame_type,frame_color," +
                "left_lens_color,right_lens_color,price,provider_id)" +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                 preparedStatement.setString(1, glasses.getBrand());
                 preparedStatement.setDouble(2, glasses.getGraduationLeft());
                 preparedStatement.setDouble(3, glasses.getGraduationRight());
                 preparedStatement.setString(4, glasses.getType());
                 preparedStatement.setString(5, glasses.getColor());
                 preparedStatement.setString(6, glasses.getColorLeft());
                 preparedStatement.setString(7, glasses.getColorRight());
                 preparedStatement.setDouble(8, glasses.getPrice());
                 preparedStatement.setObject(9, glasses.getProvider().getId());

                 preparedStatement.execute();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    glasses.setId(generatedKeys.getInt(1));
                    System.out.println("Gafas guardadas con èxito. ID asignado: " + glasses.getId());
                }
            }

            return findById(connection, glasses.getId());

            } catch (SQLException e) {
                 System.err.println(e.getMessage());
        }
        return null;
    }

    public Glasses findById(Connection connection, int id) {
        String sql = "SELECT * FROM glasses WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Glasses glasses = new Glasses(
                            rs.getString("brand"),
                            rs.getDouble("left_graduation"),
                            rs.getDouble("right_graduation"),
                            rs.getString("frame_type"),
                            rs.getString("frame_color"),
                            rs.getString("left_lens_color"),
                            rs.getString("right_lens_color"),
                            rs.getDouble("price"),
                            null
                    );

                    int providerId = rs.getInt("provider_id");
                    ProviderRepository providerRepository = new ProviderRepository();
                    Provider provider = providerRepository.findProviderById(connection, providerId);
                    glasses.setProvider(provider);

                    glasses.setId(id);

                    return glasses;
                }
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}
