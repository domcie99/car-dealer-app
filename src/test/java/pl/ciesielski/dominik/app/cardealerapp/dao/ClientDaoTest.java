package pl.ciesielski.dominik.app.cardealerapp.dao;

import org.junit.jupiter.api.*;
import pl.ciesielski.dominik.app.cardealerapp.model.Client;

import static org.junit.jupiter.api.Assertions.*;

class ClientDaoTest {

    @BeforeEach
    void setUp() {
        System.out.println("Setup"); // TODO: 04.08.2023 Napisać kod który wstawi niebędne dane do bazy danych.
    }

    @AfterEach
    void tearDown() {
        System.out.println("TearDown"); // TODO: 04.08.2023 truncate Bazy danych
    }

    // TODO: 04.08.2023 Napisac test jednostkowy dla metody addclient 
    
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
}