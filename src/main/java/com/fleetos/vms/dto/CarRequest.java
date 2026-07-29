package com.fleetos.vms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

@Schema(description = "Payload for creating a Car")
public class CarRequest {

    @NotBlank
    @Schema(example = "TS09AB1234")
    private String registrationNumber;

    @NotBlank
    @Schema(example = "Toyota Innova")
    private String model;

    @PositiveOrZero
    @Schema(example = "42.5")
    private double fuelLevel;

    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public double getFuelLevel() { return fuelLevel; }
    public void setFuelLevel(double fuelLevel) { this.fuelLevel = fuelLevel; }
}
