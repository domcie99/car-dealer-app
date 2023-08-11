package pl.ciesielski.dominik.app.cardealerapp.dao;

import pl.ciesielski.dominik.app.cardealerapp.dao.utils.DatabaseConnectionManager;
import pl.ciesielski.dominik.app.cardealerapp.model.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SellerDao {

    private static final String INSERT_SELLER = "INSERT INTO sellers (first_name, last_name, phone_number, email) VALUES (?, ?, ?, ?)";
    private static final String GET_SELLER_BY_ID = "SELECT * FROM sellers WHERE id = ?";
    private static final String GET_ALL_SELLERS = "SELECT * FROM sellers";
    private static final String GET_SELLER_BY_EMAIL = "SELECT * FROM sellers WHERE email = ?";
    private static final String UPDATE_SELLER = "UPDATE sellers SET first_name = ?, last_name = ?, phone_number = ?, email = ? WHERE id = ?";
    private static final String DELETE_SELLER = "DELETE FROM sellers WHERE id = ?";

    public void addSeller(Seller seller) {
        try (Connection connection = DatabaseConnectionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SELLER)) {

            preparedStatement.setString(1, seller.getFirstName());
            preparedStatement.setString(2, seller.getLastName());
            preparedStatement.setString(3, seller.getPhoneNumber());
            preparedStatement.setString(4, seller.getEmail());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Seller getSellerById(long id) {
        try (Connection connection = DatabaseConnectionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_SELLER_BY_ID)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return createSellerFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Seller> getAllSellers() {
        List<Seller> sellers = new ArrayList<>();
        try (Connection connection = DatabaseConnectionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_SELLERS)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                sellers.add(createSellerFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sellers;
    }

    public Seller getSellerByEmail(String email) {
        try (Connection connection = DatabaseConnectionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_SELLER_BY_EMAIL)) {

            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return createSellerFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateSeller(Seller seller) {
        try (Connection connection = DatabaseConnectionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SELLER)) {

            preparedStatement.setString(1, seller.getFirstName());
            preparedStatement.setString(2, seller.getLastName());
            preparedStatement.setString(3, seller.getPhoneNumber());
            preparedStatement.setString(4, seller.getEmail());
            preparedStatement.setLong(5, seller.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSeller(long id) {
        try (Connection connection = DatabaseConnectionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SELLER)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Seller createSellerFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String phoneNumber = resultSet.getString("phone_number");
        String sellerEmail = resultSet.getString("email");

        return new Seller(id, firstName, lastName, phoneNumber, sellerEmail);
    }
}
