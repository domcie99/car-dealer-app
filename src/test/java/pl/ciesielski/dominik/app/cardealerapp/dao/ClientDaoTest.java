package pl.ciesielski.dominik.app.cardealerapp.dao;

import org.junit.jupiter.api.*;
import pl.ciesielski.dominik.app.cardealerapp.dao.utils.DatabaseConnectionManager;
import pl.ciesielski.dominik.app.cardealerapp.dao.utils.DatabaseInitializer;
import pl.ciesielski.dominik.app.cardealerapp.controller.model.Address;
import pl.ciesielski.dominik.app.cardealerapp.controller.model.AddressBuilder;
import pl.ciesielski.dominik.app.cardealerapp.controller.model.Client;
import pl.ciesielski.dominik.app.cardealerapp.dao.utils.NextIdSequence;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class ClientDaoTest {

    private ClientDao clientDao;
    private AddressDao addressDao;

    Address address1;

    private long nextClientId;
    private long nextAddressId;

    @BeforeEach
    void setUp() throws Exception{
        System.out.println("Setup");
        DatabaseInitializer initializer = new DatabaseInitializer();
        initializer.createTables();

        nextClientId = NextIdSequence.getNextIdForTable("Clients");
        nextAddressId = NextIdSequence.getNextIdForTable("Addresses");

        addressDao = new AddressDao();

        address1 = new AddressBuilder()
                .setId(nextAddressId)
                .setStreet("123 Main St")
                .setCity("New York")
                .setZipCode("12345")
                .setCountry("USA")
                .build();
        addressDao.addAddress(address1);
    }

    @AfterEach
    void tearDown() { // TODO: 08.09.2023 DATABASEUTILS Czyszczenie bazy danych.
        try (Connection connection = DatabaseConnectionManager.getInstance().getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP ALL OBJECTS;");
            System.out.println("Database has been cleaned.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    @Test
    @Disabled
    void givenClientDaoAndEmail_whenGetClientByEmail_thenClientIsNotNull() {
        // Given - Tworzymy obiekt klasy testowanej oraz parametry metody testowanej
        ClientDao clientDao = new ClientDao();
        String email = "asd@gmail.com";

        // When - Wywołujemy metode testowaną
        Client client = clientDao.getClientByEmail(email);

        // Then - Sprawdzamy wartość oczekiwaną która jest zwracana z metody testowanej.
        Assertions.assertNotNull(client, "client is null");
    }

    @Test
    void givenClientDaoAndFakeEmail_whenGetClientByEmail_thenClientIsNull() {
        // Given
        ClientDao clientDao = new ClientDao();
        String email = "asd@gmail.com";

        // When
        Client client = clientDao.getClientByEmail(email);

        // Then
        Assertions.assertNull(client, "client is not null");
    }

    @Test
    void givenClientDaoAndValidClient_whenAddClient_thenClientIsAddedToDatabase() {
        // Given
        ClientDao clientDao = new ClientDao();
        Client client = new Client(
                nextClientId,
                "Mike",
                "Johnson",
                address1,
                "+1 555-123-4567",
                "mike.johnson@example.com");

        // When
        clientDao.addClient(client);

        // Then
        Client retrievedClient = clientDao.getClientById(client.getId());
        assertNotNull(retrievedClient, "Retrieved client is null");
        assertEquals(client.getFirstName(), retrievedClient.getFirstName(), "First names don't match");
        assertEquals(client.getLastName(), retrievedClient.getLastName(), "Last names don't match");
    }
}