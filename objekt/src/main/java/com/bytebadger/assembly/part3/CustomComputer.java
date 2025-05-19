package com.bytebadger.assembly.part3;

import java.util.HashMap;
import java.util.Map;

import no.ntnu.tdt4100.bytebadger.Customer;
import no.ntnu.tdt4100.bytebadger.IComputerPart;
import no.ntnu.tdt4100.bytebadger.ICustomBuild;


/**
 * Implement the class CustomComputer which represents a custom computer build.
 * 
 * The class must implement the interface {@link no.ntnu.tdt4100.bytebadger.ICustomBuild}.
 * Read the documentation in the interface for detailed description of the behaviour of
 * the methods you need to implement.
 * 
 * In addition to the methods required by the interface, the class has a constructor and
 * some additional methods for managing the computer build.
 * 
 * The class should store a list of {@link no.ntnu.tdt4100.bytebadger.IComputerPart} computer part
 * objects and it should be able to add and remove a quantity of parts from
 * the custom build. 
 * 
 * @see no.ntnu.tdt4100.bytebadger.ICustomBuild
 * @see CustomComputerTest
*/

public class CustomComputer implements ICustomBuild {

    private final Map<IComputerPart, Integer> parts = new HashMap<>();
    private final Customer customer;

    /**
     * This is the constructor for the class CustomComputer
     * 
     * @param customer the customer of a custom computer build
     * 
     * @throws IllegalArgumentException if the customer is null.
     */
    
    public CustomComputer(Customer customer) {
        if (customer == null) throw new IllegalArgumentException();
        this.customer = customer;
    }

    /**
     * This method adds a quantity of a specific computer part to the custom computer build.
     * 
     * @param part a computer part
     * @param quantity the number of parts to be added
     * 
     * @throws IllegalArgumentException if the quantity is not a positive integer.
     */
    
    public void addPart(IComputerPart part, int quantity) {
        if (part == null || quantity <= 0) throw new IllegalArgumentException();

        parts.compute(part, (p, n) -> n==null ? quantity : n+quantity);
    }

    /**
     * This method removes a quantity of a specific computer part from the custom computer build.
     * 
     * @param part a computer part
     * @param quantity the number of parts to be removed
     * 
     * @throws IllegalArgumentException if the part is not in the build or if the quantity is not a positive number.
     */
    public void removePart(IComputerPart part, int quantity) {
        if (parts.containsKey(part) || quantity <= 0) throw new IllegalArgumentException();

        int newVal = parts.get(part);
        if (newVal <= 0) {
            parts.remove(part);
        } else {
            parts.put(part, newVal);
        }
    }

    @Override
    public Customer getCustomer() {
        return customer;
    }

    @Override
    public double getTotalPrice() {
        double price = 0;

        for (var e : parts.entrySet()) {
            price += e.getKey().getPrice() * e.getValue();
        }

        return price;
    }

    @Override
    public Map<IComputerPart, Integer> getParts() {
        return parts;
    }
}
