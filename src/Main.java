import entity.Customer;
import entity.Driver;
import entity.Location;
import enums.STATUS;
import service.CustomerServiceImpl;
import service.DriverServiceImpl;
import service.InMemory;

import java.util.ArrayList;

import static service.InMemory.customerRepository;
import static service.InMemory.driverRepository;

public class Main {
    public static void main(String[] args) {
        // Initialize services
        InMemory inMemory = new InMemory();
        CustomerServiceImpl customerService = new CustomerServiceImpl();
        DriverServiceImpl driverService = new DriverServiceImpl();

        // Create mock data for customers and drivers
        // Creating Location instances using Builder pattern
        Location location1 =  Location.builder()
                .id(1)
                .longitude(0)
                .latitude(0)
                .build();

        Location location2 =  Location.builder()
                .id(2)
                .longitude(2)
                .latitude(1)
                .build();

        // Creating Customer instances using Builder pattern
        Customer customer1 =  Customer.builder()
                .name("John")
                .currLocation(location1)
                .currentRide(null)
                .build();

        Customer customer2 =  Customer.builder()
                .name("Alice")
                .currLocation(location2)
                .searchLocation(null)
                .currentRide(null)
                .build();

        // Creating Driver instances using Builder pattern
        Driver driver1 =  Driver.builder()
                .name("Bob")
                .location(location1)
                .status(STATUS.TRUE)
                .rides(new ArrayList<>())
                .build();

        Driver driver2 =  Driver.builder()
                .name("Charlie")
                .location(location2)
                .status(STATUS.TRUE)
                .rides(new ArrayList<>())
                .build();

        // Save customers and drivers to in-memory storage
        customerService.createCustomer(customer1);
        customerService.createCustomer(customer2);

        driverService.createDriver(driver2);
        driverService.createDriver(driver1);

        System.out.println(customerRepository.getAll());
        System.out.println(driverRepository.getAll());
        driverService.changeDriverStatus("Bob", STATUS.TRUE);
        driverService.changeDriverStatus("Charlie", STATUS.TRUE);

        // Example 1: Update customer location
//        Location newLocation = new Location(5, 1, 1);
//        customerService.updateCustomerLocation("John", newLocation);

        // Example 2: Customer searching for a ride
        customerService.findRide("John", location1, location2);

        // Example 3: Customer choosing a ride
        customerService.chooseRide("John", "Bob");

        // Example 4: Calculate bill for a customer
        customerService.calculateBill("John");

        // Example 5: Driver calculating total earnings
        driverService.findTotalEarning();
    }
}