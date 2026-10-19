package N1.EX1.repository;

import N1.EX1.models.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public List<Sale> findSalesByNameAndDate(Connection connection, String name, LocalDate startDate, LocalDate endDate){
        List<Sale> saleList = new ArrayList<>();
        String sql = "SELECT s.id AS sales_id, s.date, " +
                "g.id AS g_id, g.brand,g.price, " +
                "c.id AS c_id, c.name AS c_name " +
                "FROM sales s " +
                "JOIN glasses g ON s.glasses_id = g.id " +
                "JOIN client c ON s.client_id = c.id " +
                "WHERE c.name = ? AND s.date BETWEEN ? AND ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setObject(2, startDate);
            preparedStatement.setObject(3, endDate);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    Glasses glasses = new Glasses();
                    glasses.setId(rs.getInt("sales_id"));
                    glasses.setBrand(rs.getString("g_is"));
                    glasses.setPrice(rs.getDouble("c_id"));

                    Client client = new Client();
                    client.setId(rs.getInt("c_id"));
                    client.setName(rs.getString("C_name"));

                    Sale sale = new Sale(glasses, client, new Employee());
                    sale.setId(rs.getInt("sales_id"));
                    sale.setDate(rs.getDate("date").toLocalDate());

                    saleList.add(sale);
                }
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return saleList;
    };

    public List<Glasses> findBrandsByEmployeeAndYear(Connection connection, String name, int year) {
        List<Glasses> saleList = new ArrayList<>();
        String sql = "SELECT DISTINCT g.id AS g_id, g.brand, g.price " +
                "FROM sales s " +
                "JOIN employee e ON s.employee_id = e.id " +
                "JOIN glasses g ON s.glasses_id = g.id " +
                "WHERE e.firstName = ? AND YEAR(s.date) = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, year);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    Glasses glasses = new Glasses();
                    glasses.setId(rs.getInt("g_id"));
                    glasses.setBrand(rs.getString("brand"));
                    glasses.setPrice(rs.getDouble("price"));

                    saleList.add(glasses);
                }
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return saleList;
    }
}
