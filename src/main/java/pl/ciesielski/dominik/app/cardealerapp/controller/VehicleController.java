package pl.ciesielski.dominik.app.cardealerapp.controller;

import pl.ciesielski.dominik.app.cardealerapp.controller.model.Vehicle;

import java.util.List;
import java.util.logging.Logger;

public class VehicleController { //Warstwa interakcji z użytkownikiem. Walidacja i weryfikacja danych wprowadzonych przez użytkownika.

    private static final Logger LOGGER = Logger.getLogger(VehicleController.class.getName());

    public Vehicle create(Vehicle vehicle) {
        //System.out.println("create(" + vehicle + ")");
        LOGGER.info("create(" + vehicle + ")");
        //Walidacja i weryfikacja danych
        LOGGER.info("create(...)=" + null);
        return null;
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
