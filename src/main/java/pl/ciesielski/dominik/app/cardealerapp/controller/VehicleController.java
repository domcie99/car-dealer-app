package pl.ciesielski.dominik.app.cardealerapp.controller;

import pl.ciesielski.dominik.app.cardealerapp.api.exception.VehicleException;
import pl.ciesielski.dominik.app.cardealerapp.controller.model.Vehicle;

import java.util.List;
import java.util.logging.Logger;

public class VehicleController { //Warstwa interakcji z użytkownikiem. Walidacja i weryfikacja danych wprowadzonych przez użytkownika.

    private static final Logger LOGGER = Logger.getLogger(VehicleController.class.getName());

    public Vehicle create(Vehicle vehicle) throws VehicleException {
        //System.out.println("create(" + vehicle + ")");
        LOGGER.info("create(" + vehicle + ")");

        if (vehicle != null) {
            String brand = vehicle.getBrand();
            if (brand != null) {
                if (brand.length() < 2) {
                    LOGGER.warning("Brand is to short");
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
