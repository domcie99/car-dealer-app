package pl.ciesielski.dominik.app.cardealerapp.controller;

import pl.ciesielski.dominik.app.cardealerapp.api.exception.VehicleException;
import pl.ciesielski.dominik.app.cardealerapp.controller.model.Vehicle;
import pl.ciesielski.dominik.app.cardealerapp.service.VehicleService;

import java.util.List;
import java.util.logging.Logger;

public class VehicleController { //Warstwa interakcji z użytkownikiem. Walidacja i weryfikacja danych wprowadzonych przez użytkownika.

    private static final Logger LOGGER = Logger.getLogger(VehicleController.class.getName());

    private VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    public Vehicle create(Vehicle vehicle) throws VehicleException {
        //System.out.println("create(" + vehicle + ")");
        LOGGER.info("create(" + vehicle + ")");

        if (vehicle != null) {
            String brand = vehicle.getBrand();
            if (brand != null) {
                if (brand.length() < 2) {
                    LOGGER.warning("Brand is to short");
                    throw new VehicleException("Brand is to short");
                } else {
                    vehicleService.create(vehicle);
                }
            } else {
                LOGGER.warning("Missing brand name.");
                throw new VehicleException("Missing brand name.");
            }
        }

        LOGGER.info("create(...)=" + vehicle);
        return vehicle;
    }

    public Vehicle read(Long id) {
        return null;
    }

    public Vehicle update(Long id, Vehicle vehicle) {
        return null;
    }

    public void delete(Long id) {
    }

    public List<Vehicle> list() {
        return null;
    }
}
