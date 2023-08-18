package pl.ciesielski.dominik.app.cardealerapp.dao.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// TODO: 11.08.2023 Napisać test Jednostkowy.
// TODO: 11.08.2023 Zamiast zwracać -1 rzuca wyjątkiem

public class NextIdSequence {

    public static long getNextIdForTable(String tableName) throws SQLException {
        String sequenceName = tableName + "_seq";

        String query = "SELECT NEXT VALUE FOR " + sequenceName;

        try (Connection connection = DatabaseConnectionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }

        } catch (SQLException e) {
            throw e;
        }

        throw new SQLException("Failed to get next ID for table: " + tableName);
    }
}
