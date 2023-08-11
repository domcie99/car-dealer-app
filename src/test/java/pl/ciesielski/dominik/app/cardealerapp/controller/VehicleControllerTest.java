package pl.ciesielski.dominik.app.cardealerapp.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.ciesielski.dominik.app.cardealerapp.controller.model.Vehicle;

import static org.junit.jupiter.api.Assertions.*;

class VehicleControllerTest {



    @Test
    void create() {
        // Given
        VehicleController vehicleController = new VehicleController();
        Vehicle vehicle = new Vehicle();

        // When
        Vehicle createdVehicle = vehicleController.create(vehicle);

        // Then
        Assertions.assertNotNull(createdVehicle, "Vehicle is null");
    }
}