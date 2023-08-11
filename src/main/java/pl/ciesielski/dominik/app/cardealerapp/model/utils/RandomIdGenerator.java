package pl.ciesielski.dominik.app.cardealerapp.model.utils;
import java.util.UUID;

public class RandomIdGenerator {
    public static long generateRandomLong() {
        return UUID.randomUUID().getLeastSignificantBits();
    }
}