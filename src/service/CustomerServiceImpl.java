package service;

import entity.Customer;
import entity.Driver;
import entity.Location;
import entity.Ride;
import enums.STATUS;
import util.Helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static service.InMemory.customerRepository;
import static service.InMemory.driverRepository;

public class CustomerServiceImpl implements CustomerService{
    DriverService driverService = new DriverServiceImpl();

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomerLocation(String customerName, Location location) {
        Customer customer = customerRepository.find(customerName);
        customer.setCurrLocation(location);
       return customerRepository.update(customer);
    }

    @Override
    public void findRide(String customerName, Location start, Location end) {
        Map<String, Driver> driverMap = driverRepository.getAll();
        List<String> driverList = new ArrayList<>();
        for(Map.Entry<String,Driver> entry: driverMap.entrySet()){
            Location riderLocation = entry.getValue().getLocation();
            if (entry.getValue().getStatus().compareTo(STATUS.TRUE) == 0 && Helper.calculateDistance(start, riderLocation) < 5) {
                driverList.add(entry.getKey());
            }
        }
        if(driverList.isEmpty()){
            System.out.println("No Driver Available");
        }
        System.out.println("Choose a driver from below");
        for(int i=0;i<driverList.size();i++){
            System.out.println(driverList.get(i) + "[Available]");
        }

        //update search location of customer
        Customer customer = customerRepository.find(customerName);
        customer.setSearchLocation(end);
        customerRepository.save(customer);
        return;
    }

    @Override
    public void chooseRide(String customerName, String driverName) {
        Customer customer = customerRepository.find(customerName);
        Driver driver = driverRepository.find(driverName);
        Ride ride = Ride.builder().id(Helper.getId()).customer(customer).driver(driver).start(customer.getCurrLocation()).end(customer.getSearchLocation()).build();

        customer.setCurrentRide(ride);
        customerRepository.save(customer);

        driver.getRides().add(ride);
        driverService.changeDriverStatus(driverName,STATUS.FALSE);
        driverRepository.save(driver);

        //
        System.out.println("ride started");

    }

    @Override
    public void calculateBill(String customerName) {
        Customer customer = customerRepository.find(customerName);
        Ride ride = customer.getCurrentRide();
        Driver driver = driverRepository.find(ride.getDriver().getName());

        System.out.println("ride ended amount = " + Helper.calculateDistance(ride.getEnd(),ride.getStart())*10);
        //ride ended...
        // remove current ride from customer
        customer.setCurrentRide(null);
        //update customer location
        customer.setCurrLocation(ride.getEnd());

        customerRepository.save(customer);

        //update driver location
        driver.setLocation(ride.getEnd());
        //set driver staus
        driverService.changeDriverStatus(driver.getName(),STATUS.TRUE);
        driverRepository.save(driver);

        return;
    }
}
