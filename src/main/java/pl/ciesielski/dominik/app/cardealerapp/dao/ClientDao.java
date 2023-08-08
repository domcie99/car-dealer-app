package pl.ciesielski.dominik.app.cardealerapp.dao;

import pl.ciesielski.dominik.app.cardealerapp.model.Address;
import pl.ciesielski.dominik.app.cardealerapp.model.AddressBuilder;
import pl.ciesielski.dominik.app.cardealerapp.model.Client;
import pl.ciesielski.dominik.app.cardealerapp.dao.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {

    private Connection getConnection(){
        return DatabaseConnectionManager.getInstance().getConnection();
    }

    public void addClient(Client client) {
        try (Connection connection = getConnection()) {
            String query = "INSERT INTO clients (first_name, last_name, phone_number, email, address) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, client.getFirstName());
            preparedStatement.setString(2, client.getLastName());
            preparedStatement.setString(3, client.getPhoneNumber());
            preparedStatement.setString(4, client.getEmail());
            preparedStatement.setString(5, client.getAddress().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Client getClientByEmail(String email) {
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM clients WHERE email=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String phoneNumber = resultSet.getString("phone_number");
                String[] addressParts = resultSet.getString("address").split(",");

                Address address = new AddressBuilder()
                        .setStreet(addressParts[0])
                        .setCity(addressParts[1])
                        .setZipCode(addressParts[2])
                        .setCountry(addressParts[3])
                        .build();

                return new Client(firstName, lastName, address, phoneNumber, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM clients";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String phoneNumber = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                String[] addressParts = resultSet.getString("address").split(",");

                Address address = new AddressBuilder()
                        .setStreet(addressParts[0])
                        .setCity(addressParts[1])
                        .setZipCode(addressParts[2])
                        .setCountry(addressParts[3])
                        .build();

                clients.add(new Client(firstName, lastName, address, phoneNumber, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    public void updateClient(Client client) {
        try (Connection connection = getConnection()) {
            String query = "UPDATE clients SET first_name=?, last_name=?, phone_number=?, address=? WHERE email=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, client.getFirstName());
            preparedStatement.setString(2, client.getLastName());
            preparedStatement.setString(3, client.getPhoneNumber());
            preparedStatement.setString(4, client.getAddress().toString());
            preparedStatement.setString(5, client.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteClient(String email) {
        try (Connection connection = getConnection()) {
            String query = "DELETE FROM clients WHERE email=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
