package pl.ciesielski.dominik.app.cardealerapp.dao.repository;

import org.junit.jupiter.api.Test;
import pl.ciesielski.dominik.app.cardealerapp.dao.entity.AddressEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class AddressRepositoryTest {

    @Test
    void create() {
        // Given
        AddressRepository addressRepository = new AddressRepository();

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setStreet("123 Main St");
        addressEntity.setCity("New York");
        addressEntity.setZipCode("10001");
        addressEntity.setCountry("USA");

        // When
        addressRepository.create(addressEntity);

        // Then
        assertNotNull(addressEntity.getId(), "ID should not be null after creation");
        AddressEntity createdAddress = addressRepository.read(addressEntity.getId());
        assertNotNull(createdAddress, "Created address should not be null");
        assertEquals("123 Main St", createdAddress.getStreet(), "Street should be '123 Main St'");
        assertEquals("New York", createdAddress.getCity(), "City should be 'New York'");
        assertEquals("10001", createdAddress.getZipCode(), "Zip code should be '10001'");
        assertEquals("USA", createdAddress.getCountry(), "Country should be 'USA'");
    }

    @Test
    void read() {
        // Given
        AddressRepository addressRepository = new AddressRepository();

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setStreet("456 Elm St");
        addressEntity.setCity("Los Angeles");
        addressEntity.setZipCode("90001");
        addressEntity.setCountry("USA");
        addressRepository.create(addressEntity);

        // When
        AddressEntity readAddress = addressRepository.read(addressEntity.getId());

        // Then
        assertNotNull(readAddress, "Read address should not be null");
        assertEquals(addressEntity.getStreet(), readAddress.getStreet(), "Street should match");
        assertEquals(addressEntity.getCity(), readAddress.getCity(), "City should match");
        assertEquals(addressEntity.getZipCode(), readAddress.getZipCode(), "Zip code should match");
        assertEquals(addressEntity.getCountry(), readAddress.getCountry(), "Country should match");
    }

    @Test
    void update() {
        // Given
        AddressRepository addressRepository = new AddressRepository();

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setStreet("789 Oak St");
        addressEntity.setCity("Chicago");
        addressEntity.setZipCode("60601");
        addressEntity.setCountry("USA");
        addressRepository.create(addressEntity);

        // When
        addressEntity.setZipCode("60602");
        addressRepository.update(addressEntity);
        AddressEntity updatedAddress = addressRepository.read(addressEntity.getId());

        // Then
        assertNotNull(updatedAddress, "Updated address should not be null");
        assertEquals("60602", updatedAddress.getZipCode(), "Zip code should be updated to '60602'");
    }

    @Test
    void delete() {
        // Given
        AddressRepository addressRepository = new AddressRepository();

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setStreet("101 Pine St");
        addressEntity.setCity("San Francisco");
        addressEntity.setZipCode("94101");
        addressEntity.setCountry("USA");
        addressRepository.create(addressEntity);

        // When
        addressRepository.delete(addressEntity.getId());
        AddressEntity deletedAddress = addressRepository.read(addressEntity.getId());

        // Then
        assertNull(deletedAddress, "Deleted address should be null");
    }
}
