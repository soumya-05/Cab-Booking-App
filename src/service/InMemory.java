package service;

import repository.CustomerRepository;
import repository.DriverRepository;

public class InMemory {
    public static CustomerRepository customerRepository;
    public static DriverRepository driverRepository;
    public InMemory() {
        customerRepository =new CustomerRepository();
        driverRepository =new DriverRepository();
    }
}
