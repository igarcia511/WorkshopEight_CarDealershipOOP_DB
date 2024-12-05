package com.ps.data;

import com.ps.models.Vehicle;

import java.util.List;

public interface VehicleDAOInt {
    public List<Vehicle> searchByPriceRange(double minPrice, double maxPrice);
    public List<Vehicle> searchByMakeAndModel(String make, String model);
    public List<Vehicle> searchByYearRange(int minYear, int maxYear);
    public List<Vehicle> searchByColor(String color);
    public List<Vehicle> searchByMileageRange(int minMiles, int maxMiles);
    public List<Vehicle> searchByType(String type);
    public void listAllVehicles(List<Vehicle> vehicles);
    public List<Vehicle> addVehicle(Vehicle vehicle);
    public List<Vehicle> removeVehicle(Vehicle vehicle);
    public void makeSaleOrLease();


}
