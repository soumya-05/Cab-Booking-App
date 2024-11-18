package service;

import entity.Customer;
import entity.Location;
import entity.Ride;

import java.util.List;

public interface CustomerService {
    public Customer createCustomer(Customer customer);
    public Customer updateCustomerLocation(String customerName, Location location);
    public void findRide(String customerName, Location start, Location end);
    public void chooseRide(String customerName, String driverName);
    public void calculateBill(String customerName);

}
