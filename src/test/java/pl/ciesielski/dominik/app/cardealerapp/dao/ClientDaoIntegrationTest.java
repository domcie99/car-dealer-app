package pl.ciesielski.dominik.app.cardealerapp.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.ciesielski.dominik.app.cardealerapp.dao.utils.DatabaseConnectionManager;
import pl.ciesielski.dominik.app.cardealerapp.dao.utils.DatabaseInitializer;
import pl.ciesielski.dominik.app.cardealerapp.model.Address;
import pl.ciesielski.dominik.app.cardealerapp.model.AddressBuilder;
import pl.ciesielski.dominik.app.cardealerapp.model.Client;
import pl.ciesielski.dominik.app.cardealerapp.model.utils.RandomIdGenerator;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

class ClientDaoIntegrationTest {

    private static final String CLIENT_EMAIL_JOHN_DOE = "john1.doe@example.com";

    @BeforeEach
    void setUp() {

        DatabaseInitializer initializer = new DatabaseInitializer();
        initializer.createTables();

        AddressDao addressDao = new AddressDao();
        Address address1 = new AddressBuilder().setId(RandomIdGenerator.generateRandomLong()).setStreet("123 Main St").setCity("New York").setZipCode("12345").setCountry("USA").build();
        Address address2 = new AddressBuilder().setId(RandomIdGenerator.generateRandomLong()).setStreet("456 Elm St").setCity("Los Angeles").setZipCode("98765").setCountry("USA").build();
        addressDao.addAddress(address1);
        addressDao.addAddress(address2);

        ClientDao clientDao = new ClientDao();
        Client client1 = new Client(RandomIdGenerator.generateRandomLong(), "John", "Doe", address1, "+1 123-456-7890", "john.doe@example.com");
        Client client2 = new Client(RandomIdGenerator.generateRandomLong(), "Jane", "Smith", address2, "+1 987-654-3210", "jane.smith@example.com");
        clientDao.addClient(client1);
        clientDao.addClient(client2);
    }

    @AfterEach
    void tearDown() {
        try (Connection connection = DatabaseConnectionManager.getInstance().getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("TRUNCATE TABLE clients, sellers, vehicles, transactions, addresses");
            statement.executeUpdate("DROP TABLE IF EXISTS clients");
            statement.executeUpdate("DROP TABLE IF EXISTS sellers");
            statement.executeUpdate("DROP TABLE IF EXISTS vehicles");
            statement.executeUpdate("DROP TABLE IF EXISTS transactions");
            statement.executeUpdate("DROP TABLE IF EXISTS addresses");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // TODO: 04.08.2023 Dodać metody Setup i Teardown zawierające inicializacje i czyszczenie bazy danych.

    @Test
    void getClientByEmail() {
        // Given
        ClientDao clientDao = new ClientDao();

        // When
        Client clientByEmail = clientDao.getClientByEmail(CLIENT_EMAIL_JOHN_DOE);

        // Then
        Assertions.assertNotNull(clientByEmail, "client is null");
    }
}