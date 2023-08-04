package pl.ciesielski.dominik.app.cardealerapp.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.ciesielski.dominik.app.cardealerapp.model.Address;
import pl.ciesielski.dominik.app.cardealerapp.model.Client;

import static org.junit.jupiter.api.Assertions.*;

class ClientDaoIntegrationTest {

    private static final String CLIENT_EMAIL_JOHN_DOE = "john1.doe@example.com";

    // TODO: 04.08.2023 Dodać metody Setup i Teardown zawierające inicializacje i czyszczenie bazy danych.

    @Test
    void getClientByEmail() {
        // Given
        ClientDao clientDao = new ClientDao();
        Client client = new Client("John", "Doe",
                new Address("123 Main St", "New York", "12345", "USA"),
                "+1 123-456-7890", CLIENT_EMAIL_JOHN_DOE);

        // When
        clientDao.addClient(client);
        Client clientByEmail = clientDao.getClientByEmail(CLIENT_EMAIL_JOHN_DOE);

        // Then
        Assertions.assertNotNull(clientByEmail, "client is null");
    }
}