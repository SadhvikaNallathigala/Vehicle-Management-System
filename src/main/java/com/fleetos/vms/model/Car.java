package com.fleetos.vms.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * INHERITANCE.
 *
 * Car IS-A Vehicle: it reuses id, registrationNumber, model, fuelLevel
 * and every getter/setter Vehicle already provides, and only adds what
 * makes a car a car — in this simple model, nothing extra at all.
 */
@Entity
@DiscriminatorValue("CAR")
public class Car extends Vehicle {

    protected Car() {
        super();
    }

    public Car(String registrationNumber, String model, double fuelLevel) {
        super(registrationNumber, model, fuelLevel); // delegates shared setup to Vehicle
    }

    @Override
    public String getVehicleType() {
        return "Car";
    }

    // POLYMORPHISM: Car's own version of start()
    @Override
    public String start() {
        setRunning(true);
        return getModel() + ": engine purring.";
    }

    @Override
    public double fuelEfficiency() {
        return 15.0; // km/l
    }
}
