package com.fleetos.vms.exception;

public class VehicleNotFoundException extends RuntimeException {
    public VehicleNotFoundException(Long id) {
        super("No vehicle found with id " + id);
    }
}
