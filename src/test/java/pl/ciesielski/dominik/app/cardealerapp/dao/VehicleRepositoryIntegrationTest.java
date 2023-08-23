package pl.ciesielski.dominik.app.cardealerapp.dao;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleRepositoryIntegrationTest {
    // TODO: 23.08.2023 Dokończyć implementacje testu integracyjnego dla metody update.
    @Test
    void update() {
        // Given
        VehicleRepository vehicleRepository = new VehicleRepository();

        // When
        vehicleRepository.create(null);
        vehicleRepository.update(null);
        vehicleRepository.read(null);

        // Then

    }
}