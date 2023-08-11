package pl.ciesielski.dominik.app.cardealerapp.model.utils;

import pl.ciesielski.dominik.app.cardealerapp.dao.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NextIdSequence {
    public static long getNextIdForTable(String tableName) {
        String sequenceName = tableName.toLowerCase() + "_seq";

        try (Connection connection = DatabaseConnectionManager.getInstance().getConnection()) {
            String query = "SELECT NEXT VALUE FOR " + sequenceName;

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getLong(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
