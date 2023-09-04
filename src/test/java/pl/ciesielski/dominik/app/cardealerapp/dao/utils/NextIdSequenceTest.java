package pl.ciesielski.dominik.app.cardealerapp.dao.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NextIdSequenceTest {

    @BeforeEach
    void setUp(){
        DatabaseInitializer initializer = new DatabaseInitializer();
        initializer.createTables();
    }

    @AfterEach
    void tearDown() {
        try (Connection connection = DatabaseConnectionManager.getInstance().getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP ALL OBJECTS;");
            System.out.println("Database has been cleaned.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    void getNextIdForTable_shouldReturnNextId() throws SQLException {
        // Given
        String tableName = "Clients";

        // When
        long nextId = NextIdSequence.getNextIdForTable(tableName);

        // Then
        assertTrue(nextId > 0, "Next ID should be greater than 0");
    }

    @Test
    void getNextIdForTable_withInvalidTable_shouldThrowException() {
        // Given
        String invalidTableName = "Users";

        // When

        // Then
        assertThrows(SQLException.class, () -> NextIdSequence.getNextIdForTable(invalidTableName));
    }
}
