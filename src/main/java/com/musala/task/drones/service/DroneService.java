package com.musala.task.drones.service;

import com.musala.task.drones.exceptions.ExceedWeightException;
import com.musala.task.drones.exceptions.NotEnoughChargeException;
import com.musala.task.drones.exceptions.NotFoundException;
import com.musala.task.drones.model.Drone;
import com.musala.task.drones.model.Medication;

import java.util.List;

public interface DroneService {

    Drone register(Drone drone);
    Drone load(long droneId, List<Medication> items) throws InstantiationException, ExceedWeightException, NotFoundException, NotEnoughChargeException;
    List<Medication> getLoadedItems(long droneId) throws NotFoundException;
    List<Drone> getAvailableDrones();
    String getBatteryCapacity(long droneId) throws NotFoundException;


}
