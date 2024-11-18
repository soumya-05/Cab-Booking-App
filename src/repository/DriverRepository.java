package repository;

import entity.Driver;

import java.util.HashMap;
import java.util.Map;

public class DriverRepository {
    Map<String, Driver> driverMap = new HashMap<>();

    //Create
    public Driver save(Driver driver){
        return driverMap.putIfAbsent(driver.getName(),driver);
    }

    //Read
    public Driver find(String driverName){
        validate(driverName);
        return driverMap.get(driverName);
    }
    public Map<String,Driver> getAll(){
        return driverMap;
    }

    //Update
    public Driver update(Driver driver){
        validate(driver.getName());
        return save(driver);
    }

    //Remove
    public void delete(String driverName){
        validate(driverName);
        driverMap.remove(driverName);
    }
    private void validate(String driverName) {
        if(!driverMap.containsKey(driverName)) throw new RuntimeException("Driver not found");
    }
}
