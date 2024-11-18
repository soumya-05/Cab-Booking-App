package service;

import entity.Customer;
import entity.Driver;
import enums.STATUS;

public interface DriverService {
    public Driver createDriver(Driver driver);

    public void changeDriverStatus(String driverName, STATUS status);
    public void findTotalEarning();
}
