package service;

import entity.Driver;
import enums.STATUS;
import util.Helper;

import java.util.Map;

import static service.InMemory.driverRepository;

public class DriverServiceImpl implements DriverService{

    @Override
    public Driver createDriver(Driver driver) {
        return driverRepository.save(driver);
    }
    @Override
    public void changeDriverStatus(String driverName, STATUS status) {
        synchronized (this) {
            Driver driver = driverRepository.find(driverName);
            driver.setStatus(status);
            driverRepository.save(driver);
        }
    }



    @Override
    public void findTotalEarning() {
        Map<String,Driver> driverMap = driverRepository.getAll();
        for(Map.Entry<String,Driver> entry : driverMap.entrySet()){
            //System.out.println(entry.getValue().getRides());
            System.out.println(entry.getKey() +" earns " + Helper.calculateEarning(entry.getValue().getRides()));
        }

    }
}
