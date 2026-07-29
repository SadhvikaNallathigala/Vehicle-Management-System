package com.fleetos.vms.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;

/**
 * CLASS + ABSTRACTION.
 *
 * Vehicle is the blueprint every vehicle in the fleet is built from.
 * It is declared abstract because "a Vehicle" on its own is never a real
 * thing on the lot — only a Car, a Truck, or an ElectricCar is. It fixes
 * WHAT every vehicle must be able to do (start(), fuelEfficiency()) and
 * leaves HOW entirely to the subclass.
 *
 * @Inheritance(SINGLE_TABLE) tells JPA to store every subclass (Car,
 * Truck, ElectricCar) in one "vehicle" table, distinguished by the
 * "vehicle_type" discriminator column — the database-level mirror of
 * Java inheritance.
 */
@Entity
@Table(name = "vehicle")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "vehicle_type", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE) // keep JSON responses flat/simple
public abstract class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ---- ENCAPSULATION -------------------------------------------------
    // Every field is private. Nothing outside this class can touch
    // registrationNumber, model or fuelLevel directly — only through the
    // getters/setters below, which is what lets setFuelLevel() guard
    // against bad data (see below).
    @Column(nullable = false, unique = true)
    private String registrationNumber;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private double fuelLevel;

    @Column(nullable = false)
    private boolean running = false;

    // ---- CONSTRUCTORS ---------------------------------------------------
    // JPA requires a no-arg constructor to exist (it uses reflection to
    // rebuild objects from database rows) — kept protected so application
    // code can't accidentally build a half-empty Vehicle.
    protected Vehicle() {
    }

    // The real constructor every subclass calls via super(...). It runs
    // once, the moment a vehicle object is created, and sets up valid
    // starting state instead of leaving fields to be filled in piecemeal.
    protected Vehicle(String registrationNumber, String model, double fuelLevel) {
        this.registrationNumber = registrationNumber;
        this.model = model;
        this.setFuelLevel(fuelLevel); // routed through the guarded setter, not the field
    }

    // ---- Getters / setters (the only door into the private fields) -----
    public Long getId() {
        return id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getFuelLevel() {
        return fuelLevel;
    }

    /** Encapsulated write: a vehicle's fuel can never be pushed negative. */
    public void setFuelLevel(double fuelLevel) {
        this.fuelLevel = Math.max(0, fuelLevel);
    }

    public boolean isRunning() {
        return running;
    }

    protected void setRunning(boolean running) {
        this.running = running;
    }

    /** Common shutdown behavior every vehicle shares — no override needed. */
    public void powerOff() {
        this.running = false;
    }

    public abstract String getVehicleType();

    // ---- POLYMORPHISM ----------------------------------------------------
    // Declared here with no body; Car, Truck and ElectricCar each supply
    // their own version. Calling code (the service layer) only ever calls
    // vehicle.start() — it never needs to know, or check, which subclass
    // is actually underneath. The correct override runs automatically.
    public abstract String start();

    // ---- ABSTRACTION -------------------------------------------------
    // Same idea: the caller gets a single number back. The formula behind
    // it (and even its unit — km/l for fuel vehicles, km/kWh for electric)
    // is a detail each subclass hides inside its own implementation.
    public abstract double fuelEfficiency();
}
