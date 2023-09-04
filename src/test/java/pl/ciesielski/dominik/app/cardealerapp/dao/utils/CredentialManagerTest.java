package pl.ciesielski.dominik.app.cardealerapp.dao.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CredentialManagerTest {

    @Test
    void getUrl_shouldReturnUrl() {
        // Arrange
        CredentialManager credentialManager = CredentialManager.getInstance();

        // Act
        String url = credentialManager.getUrl();

        // Assert
        assertNotNull(url, "URL should not be null");
    }

    @Test
    void getUsername_shouldReturnUsername() {
        // Arrange
        CredentialManager credentialManager = CredentialManager.getInstance();

        // Act
        String username = credentialManager.getUsername();

        // Assert
        assertNotNull(username, "Username should not be null");
    }

    @Test
    void getPassword_shouldReturnPassword() {
        // Arrange
        CredentialManager credentialManager = CredentialManager.getInstance();

        // Act
        String password = credentialManager.getPassword();

        // Assert
        assertNotNull(password, "Password should not be null");
    }
}
