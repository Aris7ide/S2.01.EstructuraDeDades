package N1.EX1.repository;

import N1.EX1.models.Sale;

import java.sql.*;
import java.time.LocalDate;

public class SaleRepository {

    public void save(Connection connection, Sale sale){
        String sql = "INSERT INTO sales (glasses_id, client_id, date)" +
                " VALUES (?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, sale.getGlasses().getId());
            preparedStatement.setInt(2, sale.getClient().getId());
            preparedStatement.setDate(3, Date.valueOf(LocalDate.now()));

            preparedStatement.executeUpdate();

            try(ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                if (resultSet.next()) {
                    sale.setId(resultSet.getInt(1));
                    System.out.println("Sale saved successfully");
                }
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
