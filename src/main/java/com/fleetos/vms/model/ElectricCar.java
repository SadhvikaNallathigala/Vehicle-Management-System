package com.fleetos.vms.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * INHERITANCE + INTERFACE, together.
 *
 * ElectricCar extends Vehicle (it IS a vehicle) and also implements
 * Chargeable (it CAN be charged) — a class can only extend one parent,
 * but it can implement as many interfaces as it needs to promise.
 *
 * Here, fuelLevel from Vehicle is reused to mean "battery charge %"
 * instead of liters — the same encapsulated field, a different meaning,
 * entirely hidden behind chargeBattery().
 */
@Entity
@DiscriminatorValue("ELECTRIC_CAR")
public class ElectricCar extends Vehicle implements Chargeable {

    protected ElectricCar() {
        super();
    }

    public ElectricCar(String registrationNumber, String model, double batteryPercent) {
        super(registrationNumber, model, batteryPercent);
    }

    @Override
    public String getVehicleType() {
        return "ElectricCar";
    }

    // POLYMORPHISM: a third, silent version of start()
    @Override
    public String start() {
        setRunning(true);
        return getModel() + ": silent power-up.";
    }

    @Override
    public double fuelEfficiency() {
        return 6.0; // km/kWh — same method name as Car/Truck, different unit, hidden here
    }

    // Fulfilling the Chargeable contract from the INTERFACE
    @Override
    public void chargeBattery(int percent) {
        setFuelLevel(Math.min(100, percent));
    }
}
