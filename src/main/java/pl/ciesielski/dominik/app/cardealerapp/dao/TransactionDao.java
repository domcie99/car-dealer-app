package pl.ciesielski.dominik.app.cardealerapp.dao;

import pl.ciesielski.dominik.app.cardealerapp.dao.utils.DatabaseConnectionManager;
import pl.ciesielski.dominik.app.cardealerapp.controller.model.Transaction;
import pl.ciesielski.dominik.app.cardealerapp.controller.model.Vehicle;
import pl.ciesielski.dominik.app.cardealerapp.controller.model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionDao {

    private static final String INSERT_TRANSACTION = "INSERT INTO transactions (vehicle_id, client_id, price, transaction_date) VALUES (?, ?, ?, ?)";
    private static final String SELECT_TRANSACTION_BY_ID = "SELECT * FROM transactions WHERE id = ?";
    private static final String SELECT_ALL_TRANSACTIONS = "SELECT * FROM transactions";
    private static final String UPDATE_TRANSACTION = "UPDATE transactions SET vehicle_id = ?, client_id = ?, price = ?, transaction_date = ? WHERE id = ?";
    private static final String DELETE_TRANSACTION = "DELETE FROM transactions WHERE id = ?";

    private VehicleDao vehicleDao;
    private ClientDao clientDao;

    public TransactionDao() {
        this.vehicleDao = new VehicleDao();
        this.clientDao = new ClientDao();
    }

    public void addTransaction(Transaction transaction) {
        try (Connection connection = DatabaseConnectionManager.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TRANSACTION);
            preparedStatement.setLong(1, transaction.getVehicle().getId());
            preparedStatement.setLong(2, transaction.getClient().getId());
            preparedStatement.setDouble(3, transaction.getPrice());
            preparedStatement.setDate(4, new java.sql.Date(transaction.getTransactionDate().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Transaction getTransactionById(long id) {
        try (Connection connection = DatabaseConnectionManager.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TRANSACTION_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return createTransactionFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        try (Connection connection = DatabaseConnectionManager.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TRANSACTIONS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Transaction transaction = createTransactionFromResultSet(resultSet);
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    public void updateTransaction(Transaction transaction) {
        try (Connection connection = DatabaseConnectionManager.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TRANSACTION);
            preparedStatement.setLong(1, transaction.getVehicle().getId());
            preparedStatement.setLong(2, transaction.getClient().getId());
            preparedStatement.setDouble(3, transaction.getPrice());
            preparedStatement.setDate(4, new java.sql.Date(transaction.getTransactionDate().getTime()));
            preparedStatement.setLong(5, transaction.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTransaction(long id) {
        try (Connection connection = DatabaseConnectionManager.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TRANSACTION);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Transaction createTransactionFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        long vehicleId = resultSet.getLong("vehicle_id");
        long clientId = resultSet.getLong("client_id");
        double price = resultSet.getDouble("price");
        Date transactionDate = resultSet.getDate("transaction_date");

        Vehicle vehicle = vehicleDao.getVehicleById(vehicleId);
        Client client = clientDao.getClientById(clientId);

        return new Transaction(id, vehicle, client, price, transactionDate);
    }
}
