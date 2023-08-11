package pl.ciesielski.dominik.app.cardealerapp.model.utils;

import pl.ciesielski.dominik.app.cardealerapp.dao.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NextIdSequence { // TODO: 11.08.2023 Napisać test Jednostkowy. 
    public static long getNextIdForTable(String tableName) { // TODO: 11.08.2023 Zamiast zwracać -1 rzuca wyjątkiem 
        String sequenceName = tableName + "_seq";

        String query = "SELECT NEXT VALUE FOR " + sequenceName;
        try (Connection connection = DatabaseConnectionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1L;
    }
}
