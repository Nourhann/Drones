package com.musala.task.drones.service;

import com.musala.task.drones.exceptions.ExceedWeightException;
import com.musala.task.drones.exceptions.NotEnoughChargeException;
import com.musala.task.drones.exceptions.NotFoundException;
import com.musala.task.drones.model.Drone;
import com.musala.task.drones.model.Medication;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DroneService {

    Drone register(Drone drone);
    void load(long droneId, List<Medication> items,List<MultipartFile> files) throws InstantiationException, ExceedWeightException, NotFoundException, NotEnoughChargeException, IOException;
    List<Medication> getLoadedItems(long droneId) throws NotFoundException;
    List<Drone> getAvailableDrones();
    String getBatteryCapacity(long droneId) throws NotFoundException;


}
