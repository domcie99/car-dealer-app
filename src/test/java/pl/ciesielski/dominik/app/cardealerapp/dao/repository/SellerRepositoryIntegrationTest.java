package pl.ciesielski.dominik.app.cardealerapp.dao.repository;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.ciesielski.dominik.app.cardealerapp.dao.entity.SellerEntity;
import pl.ciesielski.dominik.app.cardealerapp.dao.utils.SessionFactoryManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SellerRepositoryIntegrationTest {

    private static SellerRepository sellerRepository;

    @BeforeAll
    static void setup() {
        sellerRepository = new SellerRepository();
    }

    @AfterAll
    static void cleanup() {
        SessionFactoryManager.closeSessionFactory();
    }

    @Test
    void create() {
        // Given
        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setFirstName("Bob");
        sellerEntity.setLastName("Johnson");

        // When
        sellerRepository.create(sellerEntity);

        // Then
        assertNotNull(sellerEntity.getId(), "ID should not be null after creation");
        SellerEntity createdSeller = sellerRepository.read(sellerEntity.getId());
        assertNotNull(createdSeller, "Created seller should not be null");
        assertEquals("Bob", createdSeller.getFirstName(), "First name should be 'Bob'");
        assertEquals("Johnson", createdSeller.getLastName(), "Last name should be 'Johnson'");
    }

    @Test
    void read() {
        // Given
        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setFirstName("Alice");
        sellerEntity.setLastName("Smith");
        sellerRepository.create(sellerEntity);

        // When
        SellerEntity readSeller = sellerRepository.read(sellerEntity.getId());

        // Then
        assertNotNull(readSeller, "Read seller should not be null");
        assertEquals(sellerEntity.getFirstName(), readSeller.getFirstName(), "First name should match");
        assertEquals(sellerEntity.getLastName(), readSeller.getLastName(), "Last name should match");
    }

    @Test
    void update() {
        // Given
        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setFirstName("Eva");
        sellerEntity.setLastName("Brown");
        sellerRepository.create(sellerEntity);

        // When
        sellerEntity.setLastName("White");
        sellerRepository.update(sellerEntity);
        SellerEntity updatedSeller = sellerRepository.read(sellerEntity.getId());

        // Then
        assertNotNull(updatedSeller, "Updated seller should not be null");
        assertEquals("Eva", updatedSeller.getFirstName(), "First name should still be 'Eva'");
        assertEquals("White", updatedSeller.getLastName(), "Last name should be updated to 'White'");
    }

    @Test
    void delete() {
        // Given
        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setFirstName("Michael");
        sellerEntity.setLastName("Johnson");
        sellerRepository.create(sellerEntity);

        // When
        sellerRepository.delete(sellerEntity.getId());
        SellerEntity deletedSeller = sellerRepository.read(sellerEntity.getId());

        // Then
        assertNull(deletedSeller, "Deleted seller should be null");
    }
}
