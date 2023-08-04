package pl.ciesielski.dominik.app.cardealerapp.data.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseInitializer implements DatabaseConnection {

    public void createTables() {
        try (Connection connection = getConnection()) {
            createClientsTable(connection);
            createVehiclesTable(connection);
            createTransactionsTable(connection);
            createSellersTable(connection);

            System.out.println("Tabele zostały utworzone.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createClientsTable(Connection connection) throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS clients ("
                + "first_name VARCHAR(255) NOT NULL,"
                + "last_name VARCHAR(255) NOT NULL,"
                + "phone_number VARCHAR(15) NOT NULL,"
                + "email VARCHAR(255) PRIMARY KEY,"
                + "address VARCHAR(255) NOT NULL"
                + ")";
        executeUpdateQuery(connection, query);
    }

    private void createVehiclesTable(Connection connection) throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS vehicles ("
                + "brand VARCHAR(255) NOT NULL,"
                + "model VARCHAR(255) NOT NULL,"
                + "yearOfProduction INT NOT NULL,"
                + "technicalCondition VARCHAR(255) NOT NULL,"
                + "mileage INT NOT NULL,"
                + "vinNumber VARCHAR(255) NOT NULL PRIMARY KEY,"
                + "registrationDate DATE NOT NULL"
                + ")";
        executeUpdateQuery(connection, query);
    }

    private void createTransactionsTable(Connection connection) throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS transactions ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "client_email VARCHAR(255) NOT NULL,"
                + "vin_number VARCHAR(255) NOT NULL,"
                + "price INT NOT NULL,"
                + "transaction_date DATE NOT NULL,"
                + "FOREIGN KEY (client_email) REFERENCES clients(email),"
                + "FOREIGN KEY (vin_number) REFERENCES vehicles(vinNumber)"
                + ")";
        executeUpdateQuery(connection, query);
    }

    private void createSellersTable(Connection connection) throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS sellers ("
                + "first_name VARCHAR(255) NOT NULL,"
                + "last_name VARCHAR(255) NOT NULL,"
                + "phone_number VARCHAR(15) NOT NULL,"
                + "email VARCHAR(255) PRIMARY KEY"
                + ")";
        executeUpdateQuery(connection, query);
    }

    private void executeUpdateQuery(Connection connection, String query) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        }
    }
}
