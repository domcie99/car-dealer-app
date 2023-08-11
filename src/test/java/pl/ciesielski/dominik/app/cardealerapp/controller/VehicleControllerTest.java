package pl.ciesielski.dominik.app.cardealerapp.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.ciesielski.dominik.app.cardealerapp.api.exception.VehicleException;
import pl.ciesielski.dominik.app.cardealerapp.controller.model.Vehicle;

import static org.junit.jupiter.api.Assertions.*;

class VehicleControllerTest {


    @Test
    void shouldCreateVehicleWithoutException() throws VehicleException {
        // Given
        VehicleController vehicleController = new VehicleController();
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("DS");

        // When
        Vehicle createdVehicle = vehicleController.create(vehicle);

        // Then
        Assertions.assertNotNull(createdVehicle, "Vehicle is null");
    }

    @Test
    void shouldThrowNewExceptionWhileCreatingNewVehicle() {
        // Given
        VehicleController vehicleController = new VehicleController();

        // When

        // Then
        Assertions.assertThrows(VehicleException.class, () -> vehicleController.create(new Vehicle()));
    }
}