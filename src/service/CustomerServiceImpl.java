package service;

import dao.CustomerDao;
import dao.DriverDao;
import entity.Customer;
import entity.Driver;
import entity.Location;
import entity.Ride;
import enums.STATUS;
import util.Helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static service.InMemory.customerDao;
import static service.InMemory.driverDao;

public class CustomerServiceImpl implements CustomerService{
    DriverService driverService = new DriverServiceImpl();

    @Override
    public Customer createCustomer(Customer customer) {
        return customerDao.save(customer);
    }

    @Override
    public Customer updateCustomerLocation(String customerName, Location location) {
        Customer customer = customerDao.find(customerName);
        customer.setCurrLocation(location);
       return customerDao.update(customer);
    }

    @Override
    public void findRide(String customerName, Location start, Location end) {
        Map<String, Driver> driverMap = driverDao.getAll();
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
        Customer customer = customerDao.find(customerName);
        customer.setSearchLocation(end);
        customerDao.save(customer);
        return;
    }

    @Override
    public void chooseRide(String customerName, String driverName) {
        Customer customer = customerDao.find(customerName);
        Driver driver = driverDao.find(driverName);
        Ride ride = Ride.builder().id(Helper.getId()).customer(customer).driver(driver).start(customer.getCurrLocation()).end(customer.getSearchLocation()).build();

        customer.setCurrentRide(ride);
        customerDao.save(customer);

        driver.getRides().add(ride);
        driverService.changeDriverStatus(driverName,STATUS.FALSE);
        driverDao.save(driver);

        //
        System.out.println("ride started");

    }

    @Override
    public void calculateBill(String customerName) {
        Customer customer = customerDao.find(customerName);
        Ride ride = customer.getCurrentRide();
        Driver driver = driverDao.find(ride.getDriver().getName());

        System.out.println("ride ended amount = " + Helper.calculateDistance(ride.getEnd(),ride.getStart())*10);
        //ride ended...
        // remove current ride from customer
        customer.setCurrentRide(null);
        //update customer location
        customer.setCurrLocation(ride.getEnd());

        customerDao.save(customer);

        //update driver location
        driver.setLocation(ride.getEnd());
        //set driver staus
        driverService.changeDriverStatus(driver.getName(),STATUS.TRUE);
        driverDao.save(driver);

        return;
    }
}
