package com.fleetos.vms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

@Schema(description = "Payload for creating a Truck")
public class TruckRequest {

    @NotBlank
    @Schema(example = "TS10CD5678")
    private String registrationNumber;

    @NotBlank
    @Schema(example = "Ashok Leyland")
    private String model;

    @PositiveOrZero
    @Schema(example = "80")
    private double fuelLevel;

    @PositiveOrZero
    @Schema(example = "9")
    private double cargoCapacityTons;

    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public double getFuelLevel() { return fuelLevel; }
    public void setFuelLevel(double fuelLevel) { this.fuelLevel = fuelLevel; }

    public double getCargoCapacityTons() { return cargoCapacityTons; }
    public void setCargoCapacityTons(double cargoCapacityTons) { this.cargoCapacityTons = cargoCapacityTons; }
}
