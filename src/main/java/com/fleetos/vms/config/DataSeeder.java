package com.fleetos.vms.config;

import com.fleetos.vms.model.Car;
import com.fleetos.vms.model.ElectricCar;
import com.fleetos.vms.model.Truck;
import com.fleetos.vms.repository.VehicleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final VehicleRepository repository;

    public DataSeeder(VehicleRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        repository.save(new Car("TS09AB1234", "Toyota Innova", 42.5));
        repository.save(new Truck("TS10CD5678", "Ashok Leyland", 80.0, 9.0));
        repository.save(new ElectricCar("TS11EF4321", "Tata Nexon EV", 100.0));
    }
}
