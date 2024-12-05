package com.ps.data;

import com.ps.models.Vehicle;
import com.sun.source.tree.CompoundAssignmentTree;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAOImpl implements VehicleDAOInt {
    private BasicDataSource basicDataSource;

    public VehicleDAOImpl(BasicDataSource basicDataSource) {
        this.basicDataSource = basicDataSource;
    }

    @Override
    public List<Vehicle> searchByPriceRange(double minPrice, double maxPrice) {
        String query = "SELECT * FROM vehicle WHERE price > ? AND price < ?;";
        List<Vehicle> vehiclesInPriceRange = new ArrayList<>();
        try (Connection connection = this.basicDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setDouble(1, minPrice);
            preparedStatement.setDouble(2, maxPrice);
            try (
                    ResultSet resultSet = preparedStatement.executeQuery();
            ) {
                while (resultSet.next()) {
                    Vehicle vehicle = mapVehicle(resultSet);
                    vehiclesInPriceRange.add(vehicle);
                }
                return vehiclesInPriceRange;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Vehicle> searchByMakeAndModel(String make, String model) {
        String query = "SELECT * FROM vehicle WHERE make = ? AND model = ?;";
        List<Vehicle> makeAndModelMatches = new ArrayList<>();
        try (Connection connection = this.basicDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, make);
            preparedStatement.setString(2, model);

            try (
                    ResultSet resultSet = preparedStatement.executeQuery();
            ) {
                while (resultSet.next()) {
                    Vehicle vehicle = mapVehicle(resultSet);
                    makeAndModelMatches.add(vehicle);
                }
                return makeAndModelMatches;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Vehicle> searchByYearRange(int minYear, int maxYear) {
        String query = "SELECT * FROM vehicle WHERE year >= ? AND year <= ?;";
        List<Vehicle> vehiclesInYearRange = new ArrayList<>();
        try (Connection connection = this.basicDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, minYear);
            preparedStatement.setInt(2, maxYear);

            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    Vehicle vehicle = mapVehicle(resultSet);
                    vehiclesInYearRange.add(vehicle);
                }
                return vehiclesInYearRange;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Vehicle> searchByColor(String color) {
        String query = "SELECT * FROM vehicle WHERE color = ?;";
        List<Vehicle> vehiclesWithColorMatch = new ArrayList<>();
        try (Connection connection = this.basicDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, color);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Vehicle vehicle = mapVehicle(resultSet);
                    vehiclesWithColorMatch.add(vehicle);
                }
                return vehiclesWithColorMatch;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Vehicle> searchByMileageRange(int minMiles, int maxMiles) {
        String query = "SELECT * FROM vehicle WHERE odometer >= ? AND odometer <= ?;";
        List<Vehicle> vehiclesInMileRage = new ArrayList<>();
        try (Connection connection = this.basicDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, minMiles);
            preparedStatement.setInt(2, maxMiles);
            try (
                    ResultSet resultSet = preparedStatement.executeQuery();
            ) {
                while (resultSet.next()) {
                    Vehicle vehicle = mapVehicle(resultSet);
                    vehiclesInMileRage.add(vehicle);
                }
                return vehiclesInMileRage;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Vehicle> searchByType(String type) {
        String query = "SELECT * FROM vehicle WHERE type = ?;";
        List<Vehicle> vehiclesTypeMatch = new ArrayList<>();
        try (Connection connection = this.basicDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, type);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    Vehicle vehicle = mapVehicle(resultSet);
                    vehiclesTypeMatch.add(vehicle);
                }
                return vehiclesTypeMatch;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Vehicle>  listAllVehicles(List<Vehicle> vehicles) {
        String query = "SELECT * FROM vehicle;";
        List<Vehicle> allVehicles = new ArrayList<>();
        try (
                Connection connection = this.basicDataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
               while (resultSet.next()){
                   Vehicle vehicle = mapVehicle(resultSet);
                   allVehicles.add(vehicle);
               }
           return allVehicles;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Vehicle> addVehicle(Vehicle vehicle) {
        return null;
    }

    @Override
    public List<Vehicle> removeVehicle(Vehicle vehicle) {
        return null;
    }

    @Override
    public void makeSaleOrLease() {

    }

    private Vehicle mapVehicle(ResultSet result) throws SQLException {

        String vin = result.getString("vin");
        int year = result.getInt("year");
        String make = result.getString("make");
        String model = result.getString("model");
        String type = result.getString("type");
        String color = result.getString("color");
        int odometer = result.getInt("odometer");
        double price = result.getDouble("price");
        boolean sold = result.getBoolean("sold");
        int dealership_id = result.getInt("dealership_id");
        return new Vehicle(vin, year, make, model, type, color, odometer, price, sold, dealership_id);
    }

}
