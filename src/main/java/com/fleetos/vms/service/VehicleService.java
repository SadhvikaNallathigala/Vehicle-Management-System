package com.fleetos.vms.service;

import com.fleetos.vms.dto.*;
import com.fleetos.vms.exception.VehicleNotFoundException;
import com.fleetos.vms.model.*;
import com.fleetos.vms.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository repository;

    public VehicleService(VehicleRepository repository) {
        this.repository = repository;
    }

    public List<Vehicle> getAll() {
        return repository.findAll();
    }

    public Vehicle getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new VehicleNotFoundException(id));
    }

    // ---- OBJECT creation ---------------------------------------------
    // Each of these calls `new Car(...)`, `new Truck(...)`, or
    // `new ElectricCar(...)` — a fresh OBJECT built from its CLASS via
    // its CONSTRUCTOR — then hands it to the repository to persist.
    public Vehicle addCar(CarRequest req) {
        validateRegistration(req.getRegistrationNumber());
        Car car = new Car(req.getRegistrationNumber(), req.getModel(), req.getFuelLevel());
        return repository.save(car);
    }

    public Vehicle addTruck(TruckRequest req) {
        validateRegistration(req.getRegistrationNumber());
        Truck truck = new Truck(req.getRegistrationNumber(), req.getModel(), req.getFuelLevel(), req.getCargoCapacityTons());
        return repository.save(truck);
    }

    public Vehicle addElectricCar(ElectricCarRequest req) {
        validateRegistration(req.getRegistrationNumber());
        ElectricCar car = new ElectricCar(req.getRegistrationNumber(), req.getModel(), req.getBatteryPercent());
        return repository.save(car);
    }

    // ---- POLYMORPHISM in action ----------------------------------------
    // This method has no idea whether `vehicle` is a Car, Truck or
    // ElectricCar. It calls start() once; Java resolves which override
    // actually runs based on the real object underneath.
    public String start(Long id) {
        Vehicle vehicle = getById(id);
        String message = vehicle.start();
        repository.save(vehicle);
        return message;
    }

    public String stop(Long id) {
        Vehicle vehicle = getById(id);
        vehicle.powerOff();
        repository.save(vehicle);
        return vehicle.getModel() + ": powered down.";
    }

    public Vehicle updateFuel(Long id, FuelUpdateRequest req) {
        Vehicle vehicle = getById(id);
        vehicle.setFuelLevel(req.getFuelLevel()); // still guarded by Vehicle's own setter
        return repository.save(vehicle);
    }

    // Only makes sense for vehicles that implement the Chargeable INTERFACE.
    public Vehicle charge(Long id, ChargeRequest req) {
        Vehicle vehicle = getById(id);
        if (!(vehicle instanceof Chargeable chargeable)) {
            throw new IllegalArgumentException(vehicle.getVehicleType() + " is not Chargeable");
        }
        chargeable.chargeBattery(req.getPercent());
        return repository.save(vehicle);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new VehicleNotFoundException(id);
        }
        repository.deleteById(id);
    }

    public double averageFuelLevel() {
        return repository.findAll().stream()
                .mapToDouble(Vehicle::getFuelLevel)
                .average()
                .orElse(0);
    }

    private void validateRegistration(String reg) {
        if (repository.existsByRegistrationNumber(reg)) {
            throw new IllegalArgumentException("Registration number already in use: " + reg);
        }
    }
}
