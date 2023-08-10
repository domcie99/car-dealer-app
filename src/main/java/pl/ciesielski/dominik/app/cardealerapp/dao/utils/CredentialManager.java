package pl.ciesielski.dominik.app.cardealerapp.dao.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CredentialManager {
    private static CredentialManager instance;
    private Properties properties;

    private CredentialManager() {
        properties = new Properties();
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("database.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static CredentialManager getInstance() {
        if (instance == null) {
            instance = new CredentialManager();
        }
        return instance;
    }

    public String getUrl() {
        return properties.getProperty("url");
    }

    public String getUsername() {
        return properties.getProperty("username");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }
}
