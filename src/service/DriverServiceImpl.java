package service;

import entity.Customer;
import entity.Driver;
import enums.STATUS;
import util.Helper;

import java.util.Map;

import static service.InMemory.driverDao;

public class DriverServiceImpl implements DriverService{

    @Override
    public Driver createDriver(Driver driver) {
        return driverDao.save(driver);
    }
    @Override
    public void changeDriverStatus(String driverName, STATUS status) {
        Driver driver = driverDao.find(driverName);
        driver.setStatus(status);
        driverDao.save(driver);
    }



    @Override
    public void findTotalEarning() {
        Map<String,Driver> driverMap = driverDao.getAll();
        for(Map.Entry<String,Driver> entry : driverMap.entrySet()){
            //System.out.println(entry.getValue().getRides());
            System.out.println(entry.getKey() +" earns " + Helper.calculateEarning(entry.getValue().getRides()));
        }

    }
}
