package pl.ciesielski.dominik.app.cardealerapp.data;

import pl.ciesielski.dominik.app.cardealerapp.data.jdbc.DatabaseConnection;
import pl.ciesielski.dominik.app.cardealerapp.model.Address;
import pl.ciesielski.dominik.app.cardealerapp.model.Vehicle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao implements DatabaseConnection {

    public void addVehicle(Vehicle vehicle) {
        try (Connection connection = getConnection()) {
            String query = "INSERT INTO vehicles (brand, model, yearOfProduction, technicalCondition, mileage, vinNumber, registrationDate) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
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

    public Vehicle getVehicleByVinNumber(String vinNumber) {
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM vehicles WHERE vinNumber=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, vinNumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String brand = resultSet.getString("brand");
                String model = resultSet.getString("model");
                int yearOfProduction = resultSet.getInt("yearOfProduction");
                String technicalCondition = resultSet.getString("technicalCondition");
                int mileage = resultSet.getInt("mileage");
                Date registrationDate = resultSet.getDate("registrationDate");

                return new Vehicle(brand, model, yearOfProduction, technicalCondition, mileage, vinNumber, registrationDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM vehicles";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String brand = resultSet.getString("brand");
                String model = resultSet.getString("model");
                int yearOfProduction = resultSet.getInt("yearOfProduction");
                String technicalCondition = resultSet.getString("technicalCondition");
                int mileage = resultSet.getInt("mileage");
                String vinNumber = resultSet.getString("vinNumber");
                Date registrationDate = resultSet.getDate("registrationDate");

                vehicles.add(new Vehicle(brand, model, yearOfProduction, technicalCondition, mileage, vinNumber, registrationDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public void updateVehicle(Vehicle vehicle) {
        try (Connection connection = getConnection()) {
            String query = "UPDATE vehicles SET brand=?, model=?, yearOfProduction=?, technicalCondition=?, mileage=?, registrationDate=? WHERE vinNumber=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, vehicle.getBrand());
            preparedStatement.setString(2, vehicle.getModel());
            preparedStatement.setInt(3, vehicle.getYearOfProduction());
            preparedStatement.setString(4, vehicle.getTechnicalCondition());
            preparedStatement.setInt(5, vehicle.getMileage());
            preparedStatement.setDate(6, new java.sql.Date(vehicle.getRegistrationDate().getTime()));
            preparedStatement.setString(7, vehicle.getVinNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteVehicleByVinNumber(String vinNumber) {
        try (Connection connection = getConnection()) {
            String query = "DELETE FROM vehicles WHERE vinNumber=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, vinNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
