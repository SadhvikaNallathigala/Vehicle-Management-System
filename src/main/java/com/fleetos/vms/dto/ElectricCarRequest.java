package com.fleetos.vms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

@Schema(description = "Payload for creating an ElectricCar")
public class ElectricCarRequest {

    @NotBlank
    @Schema(example = "TS11EF4321")
    private String registrationNumber;

    @NotBlank
    @Schema(example = "Tata Nexon EV")
    private String model;

    @PositiveOrZero
    @Schema(description = "Starting battery charge, as a percentage", example = "100")
    private double batteryPercent;

    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public double getBatteryPercent() { return batteryPercent; }
    public void setBatteryPercent(double batteryPercent) { this.batteryPercent = batteryPercent; }
}
