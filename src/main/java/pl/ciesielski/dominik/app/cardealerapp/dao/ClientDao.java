package pl.ciesielski.dominik.app.cardealerapp.dao;

import pl.ciesielski.dominik.app.cardealerapp.dao.utils.DatabaseConnectionManager;
import pl.ciesielski.dominik.app.cardealerapp.model.Address;
import pl.ciesielski.dominik.app.cardealerapp.model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {

    private static final String INSERT_CLIENT = "INSERT INTO clients (first_name, last_name, phone_number, email, address_id) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_CLIENT_BY_EMAIL = "SELECT * FROM clients WHERE email=?";
    private static final String SELECT_ALL_CLIENTS = "SELECT * FROM clients";
    private static final String UPDATE_CLIENT = "UPDATE clients SET first_name=?, last_name=?, phone_number=?, address_id=? WHERE email=?";
    private static final String DELETE_CLIENT = "DELETE FROM clients WHERE email=?";

    public void addClient(Client client) {
        try (Connection connection = DatabaseConnectionManager.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLIENT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, client.getFirstName());
            preparedStatement.setString(2, client.getLastName());
            preparedStatement.setString(3, client.getPhoneNumber());
            preparedStatement.setString(4, client.getEmail());
            preparedStatement.setLong(5, client.getAddress().getId());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Inserting client failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long clientId = generatedKeys.getLong(1);
                    client.setId(clientId);
                } else {
                    throw new SQLException("Inserting client failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Client getClientByEmail(String email) {
        try (Connection connection = DatabaseConnectionManager.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLIENT_BY_EMAIL);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                long clientId = resultSet.getLong("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String phoneNumber = resultSet.getString("phone_number");

                AddressDao addressDao = new AddressDao();
                Address address = addressDao.getAddressById(resultSet.getLong("address_id"));

                return new Client(clientId, firstName, lastName, address, phoneNumber, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        try (Connection connection = DatabaseConnectionManager.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CLIENTS);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long clientId = resultSet.getLong("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String phoneNumber = resultSet.getString("phone_number");
                String email = resultSet.getString("email");

                AddressDao addressDao = new AddressDao();
                Address address = addressDao.getAddressById(resultSet.getLong("address_id"));

                clients.add(new Client(clientId, firstName, lastName, address, phoneNumber, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    public void updateClient(Client client) {
        try (Connection connection = DatabaseConnectionManager.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CLIENT);
            preparedStatement.setString(1, client.getFirstName());
            preparedStatement.setString(2, client.getLastName());
            preparedStatement.setString(3, client.getPhoneNumber());
            preparedStatement.setLong(4, client.getAddress().getId());
            preparedStatement.setString(5, client.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteClient(String email) {
        try (Connection connection = DatabaseConnectionManager.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLIENT);
            preparedStatement.setString(1, email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
