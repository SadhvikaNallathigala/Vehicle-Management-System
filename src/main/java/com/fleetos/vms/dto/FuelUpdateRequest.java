package com.fleetos.vms.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Payload for updating a vehicle's fuel level")
public class FuelUpdateRequest {

    @Schema(example = "55.0")
    private double fuelLevel;

    public double getFuelLevel() { return fuelLevel; }
    public void setFuelLevel(double fuelLevel) { this.fuelLevel = fuelLevel; }
}
