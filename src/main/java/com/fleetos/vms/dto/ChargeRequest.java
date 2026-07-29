package com.fleetos.vms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Schema(description = "Payload for charging an ElectricCar's battery")
public class ChargeRequest {

    @Min(0) @Max(100)
    @Schema(example = "100")
    private int percent;

    public int getPercent() { return percent; }
    public void setPercent(int percent) { this.percent = percent; }
}
