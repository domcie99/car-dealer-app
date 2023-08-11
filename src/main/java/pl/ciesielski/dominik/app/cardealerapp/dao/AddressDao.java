package pl.ciesielski.dominik.app.cardealerapp.dao;

import pl.ciesielski.dominik.app.cardealerapp.dao.utils.DatabaseConnectionManager;
import pl.ciesielski.dominik.app.cardealerapp.controller.model.Address;
import pl.ciesielski.dominik.app.cardealerapp.controller.model.AddressBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressDao {

    private static final String INSERT_ADDRESS = "INSERT INTO addresses (street, city, zip_code, country) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ADDRESS_BY_ID = "SELECT * FROM addresses WHERE id=?";
    private static final String SELECT_ALL_ADDRESSES = "SELECT * FROM addresses";
    private static final String UPDATE_ADDRESS = "UPDATE addresses SET street=?, city=?, zip_code=?, country=? WHERE id=?";
    private static final String DELETE_ADDRESS = "DELETE FROM addresses WHERE id=?";

    public void addAddress(Address address) {
        try (Connection connection = DatabaseConnectionManager.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ADDRESS);
            preparedStatement.setString(1, address.getStreet());
            preparedStatement.setString(2, address.getCity());
            preparedStatement.setString(3, address.getZipCode());
            preparedStatement.setString(4, address.getCountry());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Address getAddressById(long id) {
        try (Connection connection = DatabaseConnectionManager.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ADDRESS_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new AddressBuilder()
                        .setId(resultSet.getLong("id"))
                        .setStreet(resultSet.getString("street"))
                        .setCity(resultSet.getString("city"))
                        .setZipCode(resultSet.getString("zip_code"))
                        .setCountry(resultSet.getString("country"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Address> getAllAddresses() {
        List<Address> addresses = new ArrayList<>();
        try (Connection connection = DatabaseConnectionManager.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ADDRESSES);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Address address = new AddressBuilder()
                        .setId(resultSet.getLong("id"))
                        .setStreet(resultSet.getString("street"))
                        .setCity(resultSet.getString("city"))
                        .setZipCode(resultSet.getString("zip_code"))
                        .setCountry(resultSet.getString("country"))
                        .build();
                addresses.add(address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addresses;
    }

    public void updateAddress(Address address) {
        try (Connection connection = DatabaseConnectionManager.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ADDRESS);
            preparedStatement.setString(1, address.getStreet());
            preparedStatement.setString(2, address.getCity());
            preparedStatement.setString(3, address.getZipCode());
            preparedStatement.setString(4, address.getCountry());
            preparedStatement.setLong(5, address.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAddress(long id) {
        try (Connection connection = DatabaseConnectionManager.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ADDRESS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
