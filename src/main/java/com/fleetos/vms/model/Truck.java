package com.fleetos.vms.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * INHERITANCE.
 *
 * Truck also extends Vehicle, but adds a field a plain Car has no use
 * for — cargoCapacityTons — showing that a subclass can extend the
 * parent's shape, not just reuse it as-is.
 */
@Entity
@DiscriminatorValue("TRUCK")
public class Truck extends Vehicle {

    @Column(name = "cargo_capacity_tons")
    private double cargoCapacityTons;

    protected Truck() {
        super();
    }

    public Truck(String registrationNumber, String model, double fuelLevel, double cargoCapacityTons) {
        super(registrationNumber, model, fuelLevel);
        this.cargoCapacityTons = cargoCapacityTons;
    }

    public double getCargoCapacityTons() {
        return cargoCapacityTons;
    }

    public void setCargoCapacityTons(double cargoCapacityTons) {
        this.cargoCapacityTons = Math.max(0, cargoCapacityTons);
    }

    @Override
    public String getVehicleType() {
        return "Truck";
    }

    // POLYMORPHISM: a heavier, different start-up behavior than Car's
    @Override
    public String start() {
        setRunning(true);
        return getModel() + ": heavy diesel idle.";
    }

    @Override
    public double fuelEfficiency() {
        return 6.5; // km/l — lower than a car, as expected for a loaded truck
    }
}
