package pl.ciesielski.dominik.app.cardealerapp.api.exception;

public class VehicleException extends Exception {
    public VehicleException(String message) {
        super(message);
    }

    public VehicleException(String message, Throwable cause) {
        super(message, cause);
    }
}
