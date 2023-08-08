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

    private Connection getConnection(){
        return DatabaseConnectionManager.getInstance().getConnection();
    }

    public void addSeller(Seller seller) {
        try (Connection connection = getConnection()) {
            String query = "INSERT INTO sellers (first_name, last_name, phone_number, email) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, seller.getFirstName());
            preparedStatement.setString(2, seller.getLastName());
            preparedStatement.setString(3, seller.getPhoneNumber());
            preparedStatement.setString(4, seller.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Seller getSellerByEmail(String email) {
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM sellers WHERE email=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String phoneNumber = resultSet.getString("phone_number");

                return new Seller(firstName, lastName, phoneNumber, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Seller> getAllSellers() {
        List<Seller> sellers = new ArrayList<>();
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM sellers";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String phoneNumber = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                sellers.add(new Seller(firstName, lastName, phoneNumber, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sellers;
    }

    public void updateSeller(Seller seller) {
        try (Connection connection = getConnection()) {
            String query = "UPDATE sellers SET first_name=?, last_name=?, phone_number=? WHERE email=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, seller.getFirstName());
            preparedStatement.setString(2, seller.getLastName());
            preparedStatement.setString(3, seller.getPhoneNumber());
            preparedStatement.setString(4, seller.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSeller(String email) {
        try (Connection connection = getConnection()) {
            String query = "DELETE FROM sellers WHERE email=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
