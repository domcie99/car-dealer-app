package pl.ciesielski.dominik.app.cardealerapp.dao;

import org.junit.jupiter.api.Test;
import pl.ciesielski.dominik.app.cardealerapp.dao.entity.VehicleEntity;

class VehicleRepositoryTest {

    @Test
    void create() {
        // Given
        VehicleRepository vehicleRepository = new VehicleRepository();

        VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setBrand("Opel");
        vehicleEntity.setModel("Astra");
        vehicleEntity.setVinNumber("ADSCG543543");

        // When
        vehicleRepository.create(vehicleEntity);

        // Then

    }
}