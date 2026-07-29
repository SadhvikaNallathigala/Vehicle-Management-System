package com.fleetos.vms.repository;

import com.fleetos.vms.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Because Vehicle is the abstract root of the hierarchy, this one
 * repository transparently reads and writes Car, Truck and ElectricCar
 * rows alike — Spring Data JPA resolves the concrete subclass for us.
 */
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Optional<Vehicle> findByRegistrationNumber(String registrationNumber);
    boolean existsByRegistrationNumber(String registrationNumber);
}
