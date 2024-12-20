package repository;

import entity.Customer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CustomerRepository {
    Map<String, Customer> customerMap = new ConcurrentHashMap<>();

    // Create and Update
    public Customer save(Customer customer) {

        return customerMap.put(customer.getName(), customer);
    }

    // Read
    public Customer find(String customerName) {
        validate(customerName);
        return customerMap.get(customerName);
    }

    public  Map<String, Customer> getAll() {
        return customerMap;
    }

    // Update
//    public Customer update(Customer customer) {
//        validate(customer.getName());
//        return save(customer);
//    }

    // Remove
    public void delete(String customerName) {
        validate(customerName);
        customerMap.remove(customerName);
    }

    private void validate(String customerName) {
        if (!customerMap.containsKey(customerName)) {
            throw new RuntimeException("Customer not found");
        }
    }

}
