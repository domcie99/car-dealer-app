package pl.ciesielski.dominik.app.cardealerapp.dao;

import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.ciesielski.dominik.app.cardealerapp.dao.utils.DatabaseConnectionManager;
import pl.ciesielski.dominik.app.cardealerapp.dao.utils.DatabaseInitializer;
import pl.ciesielski.dominik.app.cardealerapp.model.Address;
import pl.ciesielski.dominik.app.cardealerapp.model.AddressBuilder;
import pl.ciesielski.dominik.app.cardealerapp.model.Client;
import pl.ciesielski.dominik.app.cardealerapp.model.utils.RandomIdGenerator;
import pl.ciesielski.dominik.app.cardealerapp.model.utils.nextIdSequence;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

class ClientDaoIntegrationTest {

    private ClientDao clientDao;
    private AddressDao addressDao;

    @BeforeEach
    void setUp() {

        DatabaseInitializer initializer = new DatabaseInitializer();
        initializer.createTables();

        addressDao = new AddressDao();

        Address address1 = new AddressBuilder()
                .setId(nextIdSequence.getNextIdForTable("Addresses"))
                .setStreet("123 Main St")
                .setCity("New York")
                .setZipCode("12345")
                .setCountry("USA")
                .build();
        addressDao.addAddress(address1);

        Address address2 = new AddressBuilder()
                .setId(nextIdSequence.getNextIdForTable("Addresses"))
                .setStreet("456 Elm St")
                .setCity("Los Angeles")
                .setZipCode("98765")
                .setCountry("USA")
                .build();
        addressDao.addAddress(address2);

        clientDao = new ClientDao();

        Client client1 = new Client(nextIdSequence.getNextIdForTable("Clients"), "John", "Doe", address1, "+1 123-456-7890", "john.doe@example.com");
        clientDao.addClient(client1);

        Client client2 = new Client(nextIdSequence.getNextIdForTable("Clients"), "Jane", "Smith", address2, "+1 987-654-3210", "jane.smith@example.com");
        clientDao.addClient(client2);
    }

    @AfterEach
    void tearDown() {
        try (Connection connection = DatabaseConnectionManager.getInstance().getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP ALL OBJECTS;");
            System.out.println("Database has been cleaned.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // TODO: 04.08.2023 Dodać metody Setup i Teardown zawierające inicializacje i czyszczenie bazy danych.

    @Test
    void shouldReturnClientByEmailWhenClientExists() {
        // Given
        String email = "john.doe@example.com";

        // When
        Client client = clientDao.getClientByEmail(email);

        // Then
        assertNotNull(client, "Client is null");
        assertEquals(email, client.getEmail());
    }
}