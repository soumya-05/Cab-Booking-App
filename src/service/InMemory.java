package service;

import dao.CustomerDao;
import dao.DriverDao;

public class InMemory {
    public static CustomerDao customerDao;
    public static DriverDao driverDao;
    public InMemory() {
        customerDao=new CustomerDao();
        driverDao=new DriverDao();
    }
}
