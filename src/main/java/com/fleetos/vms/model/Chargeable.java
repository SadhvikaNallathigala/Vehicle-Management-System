package com.fleetos.vms.model;

/**
 * INTERFACE.
 *
 * A contract that has nothing to do with the Vehicle inheritance tree.
 * Any class can promise to be Chargeable as long as it implements this
 * method — in this system, only ElectricCar does.
 */
public interface Chargeable {
    void chargeBattery(int percent);
}
