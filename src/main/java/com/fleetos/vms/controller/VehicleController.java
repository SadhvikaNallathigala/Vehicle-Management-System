package com.fleetos.vms.controller;

import com.fleetos.vms.dto.*;
import com.fleetos.vms.model.Vehicle;
import com.fleetos.vms.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vehicles")
@Tag(name = "Vehicles", description = "Create, inspect and operate vehicles in the fleet")
public class VehicleController {

    private final VehicleService service;

    public VehicleController(VehicleService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "List every vehicle in the fleet")
    public List<Vehicle> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get one vehicle by id")
    public Vehicle getOne(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/stats")
    @Operation(summary = "Fleet-wide stats (total count, running count, average fuel)")
    public Map<String, Object> stats() {
        List<Vehicle> all = service.getAll();
        long running = all.stream().filter(Vehicle::isRunning).count();
        return Map.of(
                "total", all.size(),
                "running", running,
                "averageFuelLevel", service.averageFuelLevel()
        );
    }

    @PostMapping("/car")
    @Operation(summary = "Add a new Car", description = "Constructs a new Car object and persists it.")
    public ResponseEntity<Vehicle> addCar(@Valid @RequestBody CarRequest request) {
        return ResponseEntity.ok(service.addCar(request));
    }

    @PostMapping("/truck")
    @Operation(summary = "Add a new Truck", description = "Constructs a new Truck object and persists it.")
    public ResponseEntity<Vehicle> addTruck(@Valid @RequestBody TruckRequest request) {
        return ResponseEntity.ok(service.addTruck(request));
    }

    @PostMapping("/electric-car")
    @Operation(summary = "Add a new ElectricCar", description = "Constructs a new ElectricCar object and persists it.")
    public ResponseEntity<Vehicle> addElectricCar(@Valid @RequestBody ElectricCarRequest request) {
        return ResponseEntity.ok(service.addElectricCar(request));
    }

    @PostMapping("/{id}/start")
    @Operation(summary = "Start a vehicle", description = "Calls the polymorphic start() method — the response differs by vehicle type.")
    public Map<String, String> start(@PathVariable Long id) {
        return Map.of("message", service.start(id));
    }

    @PostMapping("/{id}/stop")
    @Operation(summary = "Stop a vehicle")
    public Map<String, String> stop(@PathVariable Long id) {
        return Map.of("message", service.stop(id));
    }

    @PatchMapping("/{id}/fuel")
    @Operation(summary = "Update a vehicle's fuel level")
    public Vehicle updateFuel(@PathVariable Long id, @RequestBody FuelUpdateRequest request) {
        return service.updateFuel(id, request);
    }

    @PostMapping("/{id}/charge")
    @Operation(summary = "Charge an ElectricCar's battery",
            description = "Only works if the vehicle implements the Chargeable interface; otherwise returns 400.")
    public Vehicle charge(@PathVariable Long id, @Valid @RequestBody ChargeRequest request) {
        return service.charge(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove a vehicle from the fleet")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
