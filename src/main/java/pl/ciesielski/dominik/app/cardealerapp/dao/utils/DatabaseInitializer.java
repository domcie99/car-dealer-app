package pl.ciesielski.dominik.app.cardealerapp.dao.utils;

import pl.ciesielski.dominik.app.cardealerapp.model.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseInitializer {

    private static final String CREATE_CLIENTS_TABLE =
            "CREATE TABLE IF NOT EXISTS clients (" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    "first_name VARCHAR(255) NOT NULL," +
                    "last_name VARCHAR(255) NOT NULL," +
                    "phone_number VARCHAR(15) NOT NULL," +
                    "email VARCHAR(255) NOT NULL UNIQUE," +
                    "address_id BIGINT NOT NULL," +
                    "FOREIGN KEY (address_id) REFERENCES addresses(id)" +
                    ")";

    private static final String CREATE_SELLERS_TABLE =
            "CREATE TABLE IF NOT EXISTS sellers (" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    "first_name VARCHAR(255) NOT NULL," +
                    "last_name VARCHAR(255) NOT NULL," +
                    "phone_number VARCHAR(15) NOT NULL," +
                    "email VARCHAR(255) NOT NULL UNIQUE" +
                    ")";

    private static final String CREATE_VEHICLES_TABLE =
            "CREATE TABLE IF NOT EXISTS vehicles (" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    "brand VARCHAR(255) NOT NULL," +
                    "model VARCHAR(255) NOT NULL," +
                    "year_of_production INT NOT NULL," +
                    "technical_condition VARCHAR(255) NOT NULL," +
                    "mileage INT NOT NULL," +
                    "vin_number VARCHAR(255) NOT NULL UNIQUE," +
                    "registration_date DATE NOT NULL" +
                    ")";

    private static final String CREATE_TRANSACTIONS_TABLE =
            "CREATE TABLE IF NOT EXISTS transactions (" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    "client_id BIGINT NOT NULL," +
                    "vin_number VARCHAR(255) NOT NULL," +
                    "price DOUBLE NOT NULL," +
                    "transaction_date DATE NOT NULL," +
                    "FOREIGN KEY (client_id) REFERENCES clients(id)," +
                    "FOREIGN KEY (vin_number) REFERENCES vehicles(vin_number)" +
                    ")";

    private static final String CREATE_ADDRESSES_TABLE =
            "CREATE TABLE IF NOT EXISTS addresses (" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    "street VARCHAR(255) NOT NULL," +
                    "city VARCHAR(255) NOT NULL," +
                    "zip_code VARCHAR(10) NOT NULL," +
                    "country VARCHAR(255) NOT NULL" +
                    ")";

    private static final String CREATE_CLIENTS_SEQUENCE =
            "CREATE SEQUENCE IF NOT EXISTS clients_seq";
    private static final String CREATE_SELLERS_SEQUENCE =
            "CREATE SEQUENCE IF NOT EXISTS sellers_seq";
    private static final String CREATE_VEHICLES_SEQUENCE =
            "CREATE SEQUENCE IF NOT EXISTS vehicles_seq";
    private static final String CREATE_TRANSACTIONS_SEQUENCE =
            "CREATE SEQUENCE IF NOT EXISTS transactions_seq";
    private static final String CREATE_ADDRESSES_SEQUENCE =
            "CREATE SEQUENCE IF NOT EXISTS addresses_seq";

    private static void executeUpdateQuery(Connection connection, String query) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        }
    }

    public void createTables() {
        try (Connection connection = DatabaseConnectionManager.getInstance().getConnection()) {
            executeUpdateQuery(connection, CREATE_ADDRESSES_TABLE);
            executeUpdateQuery(connection, CREATE_CLIENTS_TABLE);
            executeUpdateQuery(connection, CREATE_SELLERS_TABLE);
            executeUpdateQuery(connection, CREATE_VEHICLES_TABLE);
            executeUpdateQuery(connection, CREATE_TRANSACTIONS_TABLE);

            executeUpdateQuery(connection, CREATE_CLIENTS_SEQUENCE);
            executeUpdateQuery(connection, CREATE_SELLERS_SEQUENCE);
            executeUpdateQuery(connection, CREATE_VEHICLES_SEQUENCE);
            executeUpdateQuery(connection, CREATE_TRANSACTIONS_SEQUENCE);
            executeUpdateQuery(connection, CREATE_ADDRESSES_SEQUENCE);

            System.out.println("Tables have been created.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
