package pl.ciesielski.dominik.app.cardealerapp.dao;

import pl.ciesielski.dominik.app.cardealerapp.dao.utils.DatabaseConnection;
import pl.ciesielski.dominik.app.cardealerapp.model.Transaction;
import pl.ciesielski.dominik.app.cardealerapp.model.Client;
import pl.ciesielski.dominik.app.cardealerapp.model.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDao implements DatabaseConnection {
    private ClientDao clientDao;
    private VehicleDao vehicleDao;

    public TransactionDao() {
        clientDao = new ClientDao();
        vehicleDao = new VehicleDao();
    }

    public void addTransaction(Transaction transaction) {
        try (Connection connection = getConnection()) {
            String query = "INSERT INTO transactions (client_email, vin_number, price, transaction_date) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, transaction.getClient().getEmail());
            preparedStatement.setString(2, transaction.getVehicle().getVinNumber());
            preparedStatement.setDouble(3, transaction.getPrice());
            preparedStatement.setDate(4, new java.sql.Date(transaction.getTransactionDate().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Transaction getTransactionById(int id) {
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM transactions WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String clientEmail = resultSet.getString("client_email");
                Client client = clientDao.getClientByEmail(clientEmail);
                String vinNumber = resultSet.getString("vin_number");
                Vehicle vehicle = vehicleDao.getVehicleByVinNumber(vinNumber);
                double price = resultSet.getDouble("price");
                java.util.Date transactionDate = resultSet.getDate("transaction_date");

                return new Transaction(id, vehicle, client, price, transactionDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM transactions";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String clientEmail = resultSet.getString("client_email");
                Client client = clientDao.getClientByEmail(clientEmail);
                String vinNumber = resultSet.getString("vin_number");
                Vehicle vehicle = vehicleDao.getVehicleByVinNumber(vinNumber);
                double price = resultSet.getDouble("price");
                java.util.Date transactionDate = resultSet.getDate("transaction_date");

                transactions.add(new Transaction(id, vehicle, client, price, transactionDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    public void updateTransaction(Transaction transaction) {
        try (Connection connection = getConnection()) {
            String query = "UPDATE transactions SET client_email=?, vin_number=?, price=?, transaction_date=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, transaction.getClient().getEmail());
            preparedStatement.setString(2, transaction.getVehicle().getVinNumber());
            preparedStatement.setDouble(3, transaction.getPrice());
            preparedStatement.setDate(4, new java.sql.Date(transaction.getTransactionDate().getTime()));
            preparedStatement.setInt(5, transaction.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTransaction(int id) {
        try (Connection connection = getConnection()) {
            String query = "DELETE FROM transactions WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
