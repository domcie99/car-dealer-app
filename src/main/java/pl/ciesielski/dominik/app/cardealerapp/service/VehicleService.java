package pl.ciesielski.dominik.app.cardealerapp.service;

import pl.ciesielski.dominik.app.cardealerapp.api.exception.VehicleException;
import pl.ciesielski.dominik.app.cardealerapp.controller.model.Vehicle;
import pl.ciesielski.dominik.app.cardealerapp.dao.VehicleDao;

import java.util.logging.Logger;

public class VehicleService { //Warstwa logiki biznesowej. Np. Sprawdzenie numeru VIN, sprawdzenie czy klient nie jest zadłużony,

    private static final Logger LOGGER = Logger.getLogger(VehicleService.class.getName());

    private VehicleDao vehicleDao;

    public VehicleService(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    public Vehicle create(Vehicle vehicle) throws VehicleException {
        Vehicle vehicleByVin = vehicleDao.getVehicleByVin(vehicle.getVinNumber());
        if (vehicleByVin != null) {
            LOGGER.warning("Found vehicle in database.");
            throw new VehicleException("Found vehicle in database.");
        } else {
            vehicleDao.addVehicle(vehicle);
        }
        return null;
    }
}
