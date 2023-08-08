package pl.ciesielski.dominik.app.cardealerapp.dao.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager {
    private static DatabaseConnectionManager instance;
    private Connection connection;

    private DatabaseConnectionManager() {
        String url = CredentialManager.getInstance().getUrl();
        String username = CredentialManager.getInstance().getUsername();
        String password = CredentialManager.getInstance().getPassword();

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseConnectionManager getInstance() {
        if (instance == null) {
            instance = new DatabaseConnectionManager();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}