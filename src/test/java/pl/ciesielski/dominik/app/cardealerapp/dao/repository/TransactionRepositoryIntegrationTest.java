package pl.ciesielski.dominik.app.cardealerapp.dao.repository;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.ciesielski.dominik.app.cardealerapp.dao.entity.ClientEntity;
import pl.ciesielski.dominik.app.cardealerapp.dao.entity.TransactionEntity;
import pl.ciesielski.dominik.app.cardealerapp.dao.entity.VehicleEntity;
import pl.ciesielski.dominik.app.cardealerapp.dao.utils.SessionFactoryManager;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TransactionRepositoryIntegrationTest {

    private static TransactionRepository transactionRepository;

    @BeforeAll
    static void setup() {
        transactionRepository = new TransactionRepository();
    }

    @AfterAll
    static void cleanup() {
        SessionFactoryManager.closeSessionFactory();
    }

    @Test
    void create() {
        // Given
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setFirstName("John");
        clientEntity.setLastName("Doe");

        VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setBrand("Toyota");
        vehicleEntity.setModel("Camry");

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setClient(clientEntity);
        transactionEntity.setVehicle(vehicleEntity);
        transactionEntity.setPrice(25000.0);
        transactionEntity.setTransactionDate(LocalDate.now());

        // When
        transactionRepository.create(transactionEntity);

        // Then
        assertNotNull(transactionEntity.getId(), "ID should not be null after creation");
        TransactionEntity createdTransaction = transactionRepository.read(transactionEntity.getId());
        assertNotNull(createdTransaction, "Created transaction should not be null");
        assertEquals("Toyota", createdTransaction.getVehicle().getBrand(), "Brand should be 'Toyota'");
        assertEquals("Camry", createdTransaction.getVehicle().getModel(), "Model should be 'Camry'");
        assertEquals("John", createdTransaction.getClient().getFirstName(), "Client first name should be 'John'");
        assertEquals(25000.0, createdTransaction.getPrice(), 0.001, "Price should match");
    }

    @Test
    void read() {
        // Given
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setFirstName("Jane");
        clientEntity.setLastName("Smith");

        VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setBrand("Honda");
        vehicleEntity.setModel("Civic");

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setClient(clientEntity);
        transactionEntity.setVehicle(vehicleEntity);
        transactionEntity.setPrice(18000.0);
        transactionEntity.setTransactionDate(LocalDate.now());
        transactionRepository.create(transactionEntity);

        // When
        TransactionEntity readTransaction = transactionRepository.read(transactionEntity.getId());

        // Then
        assertNotNull(readTransaction, "Read transaction should not be null");
        assertEquals("Jane", readTransaction.getClient().getFirstName(), "Client first name should match");
        assertEquals("Honda", readTransaction.getVehicle().getBrand(), "Brand should match");
        assertEquals(18000.0, readTransaction.getPrice(), 0.001, "Price should match");
    }

    @Test
    void update() {
        // Given
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setFirstName("Eva");
        clientEntity.setLastName("Brown");

        VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setBrand("Nissan");
        vehicleEntity.setModel("Altima");

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setClient(clientEntity);
        transactionEntity.setVehicle(vehicleEntity);
        transactionEntity.setPrice(22000.0);
        transactionEntity.setTransactionDate(LocalDate.now());
        transactionRepository.create(transactionEntity);

        // When
        transactionEntity.setPrice(23000.0);
        transactionRepository.update(transactionEntity);
        TransactionEntity updatedTransaction = transactionRepository.read(transactionEntity.getId());

        // Then
        assertNotNull(updatedTransaction, "Updated transaction should not be null");
        assertEquals(23000.0, updatedTransaction.getPrice(), 0.001, "Price should be updated");
    }

    @Test
    void delete() {
        // Given
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setFirstName("Grace");
        clientEntity.setLastName("Adams");

        VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setBrand("Chevrolet");
        vehicleEntity.setModel("Malibu");

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setClient(clientEntity);
        transactionEntity.setVehicle(vehicleEntity);
        transactionEntity.setPrice(19000.0);
        transactionEntity.setTransactionDate(LocalDate.now());
        transactionRepository.create(transactionEntity);

        // When
        transactionRepository.delete(transactionEntity.getId());
        TransactionEntity deletedTransaction = transactionRepository.read(transactionEntity.getId());

        // Then
        assertNull(deletedTransaction, "Deleted transaction should be null");
    }
}
