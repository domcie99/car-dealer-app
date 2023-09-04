package pl.ciesielski.dominik.app.cardealerapp.dao.repository;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.ciesielski.dominik.app.cardealerapp.dao.entity.VehicleEntity;
import pl.ciesielski.dominik.app.cardealerapp.dao.utils.SessionFactoryManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class VehicleRepositoryIntegrationTest {

    private static VehicleRepository vehicleRepository;

    @BeforeAll
    static void setup() {
        vehicleRepository = new VehicleRepository();
    }

    @AfterAll
    static void cleanup() {
        SessionFactoryManager.closeSessionFactory();
    }

    @Test
    void create() {
        // Given
        VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setBrand("Opel");
        vehicleEntity.setModel("Astra");
        vehicleEntity.setVinNumber("ADSCG543543");

        // When
        vehicleRepository.create(vehicleEntity);

        // Then
        assertNotNull(vehicleEntity.getId(), "ID should not be null after creation");
        VehicleEntity createdVehicle = vehicleRepository.read(vehicleEntity.getId());
        assertNotNull(createdVehicle, "Created vehicle should not be null");
        assertEquals("Opel", createdVehicle.getBrand(), "Brand should be 'Opel'");
        assertEquals("Astra", createdVehicle.getModel(), "Model should be 'Astra'");
        assertEquals("ADSCG543543", createdVehicle.getVinNumber(), "VIN should be 'ADSCG543543'");
    }

    @Test
    void read() {
        // Given
        VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setBrand("Ford");
        vehicleEntity.setModel("Focus");
        vehicleEntity.setVinNumber("WXYZ12345");
        vehicleRepository.create(vehicleEntity);

        // When
        VehicleEntity readVehicle = vehicleRepository.read(vehicleEntity.getId());

        // Then
        assertNotNull(readVehicle, "Read vehicle should not be null");
        assertEquals(vehicleEntity.getBrand(), readVehicle.getBrand(), "Brand should match");
        assertEquals(vehicleEntity.getModel(), readVehicle.getModel(), "Model should match");
        assertEquals(vehicleEntity.getVinNumber(), readVehicle.getVinNumber(), "VIN should match");
    }

    @Test
    void update() {
        // Given
        VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setBrand("Peugeot");
        vehicleEntity.setModel("4008");
        vehicleRepository.create(vehicleEntity);

        // When
        vehicleEntity.setModel("5008");
        vehicleRepository.update(vehicleEntity);
        VehicleEntity updatedVehicle = vehicleRepository.read(vehicleEntity.getId());

        // Then
        assertNotNull(updatedVehicle, "Updated vehicle should not be null");
        assertEquals("Peugeot", updatedVehicle.getBrand(), "Brand should still be 'Peugeot'");
        assertEquals("5008", updatedVehicle.getModel(), "Model should be updated to '5008'");
    }

    @Test
    void delete() {
        // Given
        VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setBrand("Toyota");
        vehicleEntity.setModel("Camry");
        vehicleRepository.create(vehicleEntity);

        // When
        vehicleRepository.delete(vehicleEntity.getId());
        VehicleEntity deletedVehicle = vehicleRepository.read(vehicleEntity.getId());

        // Then
        assertNull(deletedVehicle, "Deleted vehicle should be null");
    }
}
