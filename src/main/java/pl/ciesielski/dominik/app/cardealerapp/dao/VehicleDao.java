package pl.ciesielski.dominik.app.cardealerapp.dao;

import pl.ciesielski.dominik.app.cardealerapp.dao.utils.DatabaseConnectionManager;
import pl.ciesielski.dominik.app.cardealerapp.controller.model.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {

    private static final String INSERT_VEHICLE = "INSERT INTO vehicles (brand, model, year_of_production, technical_condition, mileage, vin_number, registration_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_VEHICLE_BY_ID = "SELECT * FROM vehicles WHERE id = ?";
    private static final String GET_ALL_VEHICLES = "SELECT * FROM vehicles";
    private static final String UPDATE_VEHICLE = "UPDATE vehicles SET brand = ?, model = ?, year_of_production = ?, technical_condition = ?, mileage = ?, vin_number = ?, registration_date = ? WHERE id = ?";
    private static final String DELETE_VEHICLE = "DELETE FROM vehicles WHERE id = ?";

    public void addVehicle(Vehicle vehicle) {
        try (Connection connection = DatabaseConnectionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_VEHICLE)) {

            preparedStatement.setString(1, vehicle.getBrand());
            preparedStatement.setString(2, vehicle.getModel());
            preparedStatement.setInt(3, vehicle.getYearOfProduction());
            preparedStatement.setString(4, vehicle.getTechnicalCondition());
            preparedStatement.setInt(5, vehicle.getMileage());
            preparedStatement.setString(6, vehicle.getVinNumber());
            preparedStatement.setDate(7, new java.sql.Date(vehicle.getRegistrationDate().getTime()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Vehicle getVehicleById(long id) {
        try (Connection connection = DatabaseConnectionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_VEHICLE_BY_ID)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return createVehicleFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = DatabaseConnectionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_VEHICLES)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                vehicles.add(createVehicleFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public void updateVehicle(Vehicle vehicle) {
        try (Connection connection = DatabaseConnectionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_VEHICLE)) {

            preparedStatement.setString(1, vehicle.getBrand());
            preparedStatement.setString(2, vehicle.getModel());
            preparedStatement.setInt(3, vehicle.getYearOfProduction());
            preparedStatement.setString(4, vehicle.getTechnicalCondition());
            preparedStatement.setInt(5, vehicle.getMileage());
            preparedStatement.setString(6, vehicle.getVinNumber());
            preparedStatement.setDate(7, new java.sql.Date(vehicle.getRegistrationDate().getTime()));
            preparedStatement.setLong(8, vehicle.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteVehicle(long id) {
        try (Connection connection = DatabaseConnectionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_VEHICLE)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Vehicle createVehicleFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String brand = resultSet.getString("brand");
        String model = resultSet.getString("model");
        int yearOfProduction = resultSet.getInt("year_of_production");
        String technicalCondition = resultSet.getString("technical_condition");
        int mileage = resultSet.getInt("mileage");
        String vinNumber = resultSet.getString("vin_number");
        java.sql.Date registrationDate = resultSet.getDate("registration_date");

        return new Vehicle(id, brand, model, yearOfProduction, technicalCondition, mileage, vinNumber, registrationDate);
    }
}
