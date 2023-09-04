package pl.ciesielski.dominik.app.cardealerapp.dao.repository;

import org.junit.jupiter.api.Test;
import pl.ciesielski.dominik.app.cardealerapp.dao.entity.AddressEntity;
import pl.ciesielski.dominik.app.cardealerapp.dao.entity.ClientEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class ClientRepositoryTest {

    @Test
    void create() {
        // Given
        ClientRepository clientRepository = new ClientRepository();

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setFirstName("John");
        clientEntity.setLastName("Doe");
        clientEntity.setPhoneNumber("123456789");
        clientEntity.setEmail("johndoe@example.com");

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setStreet("123 Main St");
        addressEntity.setCity("New York");
        addressEntity.setZipCode("10001");
        addressEntity.setCountry("USA");
        clientEntity.setAddress(addressEntity);

        // When
        clientRepository.create(clientEntity);

        // Then
        assertNotNull(clientEntity.getId(), "ID should not be null after creation");
        ClientEntity createdClient = clientRepository.read(clientEntity.getId());
        assertNotNull(createdClient, "Created client should not be null");
        assertEquals("John", createdClient.getFirstName(), "First name should be 'John'");
        assertEquals("Doe", createdClient.getLastName(), "Last name should be 'Doe'");
        assertEquals("123456789", createdClient.getPhoneNumber(), "Phone number should be '123456789'");
        assertEquals("johndoe@example.com", createdClient.getEmail(), "Email should be 'johndoe@example.com'");
    }

    @Test
    void read() {
        // Given
        ClientRepository clientRepository = new ClientRepository();

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setFirstName("Alice");
        clientEntity.setLastName("Smith");
        clientEntity.setPhoneNumber("987654321");
        clientEntity.setEmail("alicesmith@example.com");

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setStreet("456 Elm St");
        addressEntity.setCity("Los Angeles");
        addressEntity.setZipCode("90001");
        addressEntity.setCountry("USA");
        clientEntity.setAddress(addressEntity);

        clientRepository.create(clientEntity);

        // When
        ClientEntity readClient = clientRepository.read(clientEntity.getId());

        // Then
        assertNotNull(readClient, "Read client should not be null");
        assertEquals(clientEntity.getFirstName(), readClient.getFirstName(), "First name should match");
        assertEquals(clientEntity.getLastName(), readClient.getLastName(), "Last name should match");
        assertEquals(clientEntity.getPhoneNumber(), readClient.getPhoneNumber(), "Phone number should match");
        assertEquals(clientEntity.getEmail(), readClient.getEmail(), "Email should match");
    }

    @Test
    void update() {
        // Given
        ClientRepository clientRepository = new ClientRepository();

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setFirstName("Emily");
        clientEntity.setLastName("Brown");
        clientEntity.setPhoneNumber("555555555");
        clientEntity.setEmail("emilybrown@example.com");

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setStreet("789 Oak St");
        addressEntity.setCity("Chicago");
        addressEntity.setZipCode("60601");
        addressEntity.setCountry("USA");
        clientEntity.setAddress(addressEntity);

        clientRepository.create(clientEntity);

        // When
        clientEntity.setPhoneNumber("999999999");
        clientRepository.update(clientEntity);
        ClientEntity updatedClient = clientRepository.read(clientEntity.getId());

        // Then
        assertNotNull(updatedClient, "Updated client should not be null");
        assertEquals("Emily", updatedClient.getFirstName(), "First name should still be 'Emily'");
        assertEquals("999999999", updatedClient.getPhoneNumber(), "Phone number should be updated to '999999999'");
    }

    @Test
    void delete() {
        // Given
        ClientRepository clientRepository = new ClientRepository();

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setFirstName("Michael");
        clientEntity.setLastName("Johnson");
        clientEntity.setPhoneNumber("777777777");
        clientEntity.setEmail("michaeljohnson@example.com");

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setStreet("101 Pine St");
        addressEntity.setCity("San Francisco");
        addressEntity.setZipCode("94101");
        addressEntity.setCountry("USA");
        clientEntity.setAddress(addressEntity);

        clientRepository.create(clientEntity);

        // When
        clientRepository.delete(clientEntity.getId());
        ClientEntity deletedClient = clientRepository.read(clientEntity.getId());

        // Then
        assertNull(deletedClient, "Deleted client should be null");
    }
}
