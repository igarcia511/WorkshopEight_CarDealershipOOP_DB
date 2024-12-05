package com.ps;

import com.ps.data.VehicleDAOImpl;
import com.ps.data.VehicleDAOInt;
import com.ps.models.Vehicle;
import org.apache.commons.dbcp2.BasicDataSource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    static Scanner commandScanner = new Scanner(System.in);
    static Scanner inputScanner = new Scanner(System.in);
    private static BasicDataSource basicDataSource = new BasicDataSource();
    private static VehicleDAOImpl vehicleDAO = new VehicleDAOImpl(basicDataSource);

    public UserInterface() {
    }


    private static void init() {

    }

    public static void display(String userName, String password) {
        basicDataSource.setUsername(userName);
        basicDataSource.setPassword(password);
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/dealership_ws");

        init();

        try {
            int mainMenuCommand;
            do {
                System.out.println("1) Find vehicles within a price range");
                System.out.println("2) Find vehicles by make / model");
                System.out.println("3) Find vehicles by year range");
                System.out.println("4) Find vehicles by color");
                System.out.println("5) Find vehicles by mileage range");
                System.out.println("6) Find vehicles by type (car, truck, SUV, van)");
                System.out.println("7) List ALL vehicles");
                System.out.println("8) Add a vehicle");
                System.out.println("9) Remove a vehicle");
                System.out.println("10) Sell/Lease a vehicle");
                System.out.println("99) to quit. . .");

                System.out.print("Command: ");
                mainMenuCommand = commandScanner.nextInt();

                switch (mainMenuCommand) {
                    case 1:
                        processGetByPriceRequest();
                        break;
                    case 2:
                        processGetByMakeModelRequest();
                        break;
                    case 3:
                        processGetByYearRequest();
                        break;
                    case 4:
                        processGetByColorRequest();
                        break;
                    case 5:
                        processGetByMileageRequest();
                        break;
                    case 6:
                        processGetByVehicleTypeRequest();
                        break;
                    case 7:
                        processGetAllVehiclesRequest();
                        break;
//                        case 8:
//                            processAddVehicleRequest();
//                            break;
//                        case 9:
//                            processRemoveVehicleRequest();
//                            break;
//                        case 10:
//                            processSaleOrLeaseVehicle();
//                            break;
                    case 99:
                        System.out.println("Exiting the program. . . ");
                        break;
                    default:
                        System.out.println("Invalid command, try again. . .");
                        break;

                }


            } while (mainMenuCommand != 99);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


    public static void processGetByPriceRequest() {
        System.out.println("Searching by price:");
        System.out.print("Enter the minimum price: ");
        double minPrice = inputScanner.nextInt();
        inputScanner.nextLine();
        System.out.print("Enter the maximum price: ");
        double maxPrice = inputScanner.nextInt();
        inputScanner.nextLine();

        List<Vehicle> vehiclesByPrice = vehicleDAO.searchByPriceRange(minPrice, maxPrice);
        if (vehiclesByPrice.isEmpty()) {
            System.out.println("No vehicles found for desired price range, try again. . . \n");
        } else {
            displayVehicles(vehiclesByPrice);
        }


    }

    public static void processGetByMakeModelRequest() {
        System.out.println("Searching by make and model");
        System.out.print("Enter the make: ");
        String make = inputScanner.nextLine();
        System.out.print("Enter the model: ");
        String model = inputScanner.nextLine();
        List<Vehicle> vehiclesByMakeAndModel = vehicleDAO.searchByMakeAndModel(make, model);
        if (vehiclesByMakeAndModel.isEmpty()) {
            System.out.println("No vehicles found for make and model, try again. . .\n");
        } else {
            displayVehicles(vehiclesByMakeAndModel);
        }


    }

    public static void processGetByYearRequest() {
        System.out.print("Enter the start year: ");
        int startYear = inputScanner.nextInt();
        inputScanner.nextLine();
        System.out.print("Enter the end year: ");
        int endYear = inputScanner.nextInt();
        List<Vehicle> vehiclesInYearRange = vehicleDAO.searchByYearRange(startYear, endYear);
        if (vehiclesInYearRange.isEmpty()) {
            System.out.println("No vehicles found for desired year range, try again. . .\n");
        } else {
            displayVehicles(vehiclesInYearRange);
        }

    }

    public static void processGetByColorRequest() {
        System.out.print("Enter the color you are looking for:");
        String userColorChoice = inputScanner.nextLine();
        List<Vehicle> vehicleColorMatches = vehicleDAO.searchByColor(userColorChoice);
        if (vehicleColorMatches.isEmpty()) {
            System.out.println("No vehicles found for your desired color, try again. . .\n");
        } else {
            displayVehicles(vehicleColorMatches);
        }


    }

    public static void processGetByMileageRequest() {
        System.out.print("Enter the minimum miles:");
        int minMiles = inputScanner.nextInt();
        inputScanner.nextLine();
        System.out.print("Enter the maximum miles:");
        int maxMiles = inputScanner.nextInt();
        inputScanner.nextLine();
        List<Vehicle> vehiclesWithinOdometerRange = vehicleDAO.searchByMileageRange(minMiles, maxMiles);
        if (vehiclesWithinOdometerRange.isEmpty()) {
            System.out.println("No vehicles found for your desired mileage range, try again. . .");
        } else {
            displayVehicles(vehiclesWithinOdometerRange);
        }

    }

    public static void processGetByVehicleTypeRequest() {
        System.out.print("Enter the type of vehicle you are searching for(suv, truck, sedan): ");
        String vehicleType = inputScanner.nextLine();
        List<Vehicle> vehiclesMatchType = vehicleDAO.searchByType(vehicleType);
        if (vehiclesMatchType.isEmpty()) {
            System.out.println("No match for your desiered type, try again. . . ");
        } else {
            displayVehicles(vehiclesMatchType);
        }

    }

    public static void processGetAllVehiclesRequest() {
        System.out.println("Displaying all vehicles");
        List<Vehicle> allVehicles = vehicleDAO.listAllVehicles();


    }

    //
//        public static void processAddVehicleRequest() {
//            System.out.println("Enter details of vehicle to add:");
//            //int vin, int year, String make, String model, String vehicleType, String color, int odometer, double price
//            System.out.print("Enter the vin number: ");
//            int vinNumber = inputScanner.nextInt();
//            inputScanner.nextLine();
//            System.out.print("Enter the year:");
//            int yearNumber = inputScanner.nextInt();
//            inputScanner.nextLine();
//            System.out.print("Enter the make:");
//            String make = inputScanner.nextLine();
//            System.out.print("Enter the model:");
//            String model = inputScanner.nextLine();
//            System.out.print("Enter vehicle type(suv, truck, sedan):");
//            String type = inputScanner.nextLine();
//            System.out.print("Enter the color: ");
//            String color = inputScanner.nextLine();
//            System.out.print("Enter the mileage: ");
//            int mileage = inputScanner.nextInt();
//            inputScanner.nextLine();
//            System.out.print("Enter the price: ");
//            double price = inputScanner.nextDouble();
//
//            Vehicle vehicle = new Vehicle(vinNumber, yearNumber, make, model, type, color, mileage, price);
//            dealership.addVehicle(vehicle);
//            DealershipFileManager.saveDealership(dealership);
//        }
//
//        public static void processRemoveVehicleRequest() {
//
//            int index = 1;
//            List<Vehicle> vehicles = dealership.getAllVehicles();
//            for (Vehicle vehicle : vehicles) {
//                System.out.printf(index + " " + "%d|%d|%s|%s|%s|%s|%d|%.2f\n",
//                        vehicle.getVin(),
//                        vehicle.getYear(),
//                        vehicle.getMake(),
//                        vehicle.getModel(),
//                        vehicle.getVehicleType(),
//                        vehicle.getColor(),
//                        vehicle.getOdometer(),
//                        vehicle.getPrice());
//                index++;
//            }
//
//            System.out.println("Select the vehicle you want to remove by its number");
//            int vehicleNumber = inputScanner.nextInt();
//            Vehicle vehicle = vehicles.get(vehicleNumber - 1);
//
//            if (vehicleNumber < 1) {
//                System.out.println("Invalid vehicle");
//            } else {
//                dealership.removeVehicle(vehicle);
//            }
//            DealershipFileManager.saveDealership(dealership);
//
//
//        }
//
//        private static void processSaleOrLeaseVehicle() {
//            //as user if it is a sale or a lease
//            // collect basic sales informations
//            // add vehicle to contract
//            // calculate pricing
//            int buyOption;
//            List<Vehicle> vehiclesToRemove = new ArrayList<>();
//            System.out.println("Enter 1) to buy a vehicle, 2) to lease a vehicle: ");
//            buyOption = inputScanner.nextInt();
//            inputScanner.nextLine();
//
//            if (buyOption == 1) {
//
//                System.out.print("Enter date: ");
//                String date = inputScanner.nextLine();
//                System.out.print("Enter name: ");
//                String name = inputScanner.nextLine();
//                System.out.print("Enter email: ");
//                String email = inputScanner.nextLine();
//                System.out.print("Do you want to finance? 1) for yes 2) for no: ");
//                int financeOrNo = inputScanner.nextInt();
//                inputScanner.nextLine();
//                boolean financeDecision;
//                if (financeOrNo == 1) {
//                    financeDecision = true;
//                } else if (financeOrNo == 2) {
//                    financeDecision = false;
//                } else {
//                    financeDecision = false;
//                }
//                for (Vehicle vehicle : dealership.getAllVehicles()) {
//                    displayVehicles(vehicle);
//
//                }
//                System.out.print("Enter the vin of vehicle you want to buy:");
//                int vehicleVinNumber = inputScanner.nextInt();
//
//
//                for (Vehicle vehicle : dealership.getAllVehicles()) {
//                    if (vehicle.getVin() == vehicleVinNumber) {
//                        SalesContract sale = new SalesContract(date, name, email, vehicle, financeDecision);
//                        ContractFileManager.saveContract(sale);
//                        vehiclesToRemove.add(vehicle);
//                    }
//
//                }
//
//
//            } else if (buyOption == 2) {
//                System.out.print("Enter date: ");
//                String date = inputScanner.nextLine();
//                System.out.print("Enter name: ");
//                String name = inputScanner.nextLine();
//                System.out.print("Enter email: ");
//                String email = inputScanner.nextLine();
//                for (Vehicle vehicle : dealership.getAllVehicles()) {
//                    LocalDate currentDate = LocalDate.now();
//                    LocalDate dateMinusThreeYears = currentDate.minusYears(3);
//                    int yearMinusThree = dateMinusThreeYears.getYear();
//                    if(vehicle.getYear() > yearMinusThree){
//                        displayVehicles(vehicle);
//                    }
//                }
//                System.out.println("Enter the vin of the vehicle you want to lease:");
//                int vehicleNumber = inputScanner.nextInt();
//
//                for(Vehicle vehicle : dealership.getAllVehicles()){
//                    if(vehicle.getVin() == vehicleNumber){
//                        LeaseContract lease = new LeaseContract(date, name, email, vehicle);
//                        ContractFileManager.saveContract(lease);
//                        vehiclesToRemove.add(vehicle);
//
//                    }
//
//                }
//            }
//            for(Vehicle vehicle: vehiclesToRemove){
//                dealership.removeVehicle(vehicle);
//            }
//        }
//
    private static void displayVehicles(List<Vehicle> vehicles) {
        System.out.printf("%-17s\t%-6s\t%-15s\t%-15s\t%-15s\t%-11s\t%-10s\t%-10s\t%-6s\t%-6s \n",
                "VIN", "YEAR", "MAKE", "MODEL", "TYPE", "COLOR", "ODOMETER", "PRICE", "SOLD", "DEALERSHIP_ID");
        System.out.println("---------------------------------------------------------------------------------" +
                "----------------------------------------------------");
        for (Vehicle vehicle : vehicles) {
            System.out.printf("%-15s\t%-6d\t%-15s\t%-15s\t%-15s\t%-10s\t%-10d\t%-10.2f\t%-6b\t%-6d\n",
                    vehicle.getVin(), vehicle.getYear(), vehicle.getMake(), vehicle.getModel(), vehicle.getVehicleType(),
                    vehicle.getColor(), vehicle.getOdometer(), vehicle.getPrice(), vehicle.isSold(), vehicle.getDealership_id());

        }

    }

}
