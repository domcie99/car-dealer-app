package pl.ciesielski.dominik.app.cardealerapp.dao.repository;

import org.junit.jupiter.api.Test;
import pl.ciesielski.dominik.app.cardealerapp.dao.entity.SellerEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class SellerRepositoryTest {

    @Test
    void create() {
        // Given
        SellerRepository sellerRepository = new SellerRepository();

        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setFirstName("John");
        sellerEntity.setLastName("Doe");
        sellerEntity.setPhoneNumber("+1 123-456-7890");
        sellerEntity.setEmail("john.doe@example.com");

        // When
        sellerRepository.create(sellerEntity);

        // Then
        assertNotNull(sellerEntity.getId(), "ID should not be null after creation");
        SellerEntity createdSeller = sellerRepository.read(sellerEntity.getId());
        assertNotNull(createdSeller, "Created seller should not be null");
        assertEquals("John", createdSeller.getFirstName(), "First name should be 'John'");
        assertEquals("Doe", createdSeller.getLastName(), "Last name should be 'Doe'");
        assertEquals("+1 123-456-7890", createdSeller.getPhoneNumber(), "Phone number should match");
        assertEquals("john.doe@example.com", createdSeller.getEmail(), "Email should match");
    }

    @Test
    void read() {
        // Given
        SellerRepository sellerRepository = new SellerRepository();

        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setFirstName("Jane");
        sellerEntity.setLastName("Smith");
        sellerEntity.setPhoneNumber("+1 987-654-3210");
        sellerEntity.setEmail("jane.smith@example.com");
        sellerRepository.create(sellerEntity);

        // When
        SellerEntity readSeller = sellerRepository.read(sellerEntity.getId());

        // Then
        assertNotNull(readSeller, "Read seller should not be null");
        assertEquals(sellerEntity.getFirstName(), readSeller.getFirstName(), "First name should match");
        assertEquals(sellerEntity.getLastName(), readSeller.getLastName(), "Last name should match");
        assertEquals(sellerEntity.getPhoneNumber(), readSeller.getPhoneNumber(), "Phone number should match");
        assertEquals(sellerEntity.getEmail(), readSeller.getEmail(), "Email should match");
    }

    @Test
    void update() {
        // Given
        SellerRepository sellerRepository = new SellerRepository();

        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setFirstName("Robert");
        sellerEntity.setLastName("Johnson");
        sellerEntity.setPhoneNumber("+1 111-222-3333");
        sellerEntity.setEmail("robert.j@example.com");
        sellerRepository.create(sellerEntity);

        // When
        sellerEntity.setEmail("robert.johnson@example.com");
        sellerRepository.update(sellerEntity);
        SellerEntity updatedSeller = sellerRepository.read(sellerEntity.getId());

        // Then
        assertNotNull(updatedSeller, "Updated seller should not be null");
        assertEquals("robert.johnson@example.com", updatedSeller.getEmail(), "Email should be updated");
    }

    @Test
    void delete() {
        // Given
        SellerRepository sellerRepository = new SellerRepository();

        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setFirstName("Alice");
        sellerEntity.setLastName("Brown");
        sellerEntity.setPhoneNumber("+1 999-888-7777");
        sellerEntity.setEmail("alice.b@example.com");
        sellerRepository.create(sellerEntity);

        // When
        sellerRepository.delete(sellerEntity.getId());
        SellerEntity deletedSeller = sellerRepository.read(sellerEntity.getId());

        // Then
        assertNull(deletedSeller, "Deleted seller should be null");
    }
}
