package N1.EX1.repository;

import N1.EX1.models.Glasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GlassesRepository {

    public void save(Connection connection, Glasses glasses) {
        String sql = "INSERT INTO glasses (brand,left_graduation,right_graduation,frame_type,frame_color," +
                "left_lens_color,right_lens_color,price,provider_id)" +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

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
                 System.out.println("Save successful");

             } catch (SQLException e) {
                 System.err.println(e.getMessage());
        }
    }
}
