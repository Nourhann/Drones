package com.musala.task.drones.controller;

import com.musala.task.drones.exceptions.ExceedWeightException;
import com.musala.task.drones.exceptions.NotEnoughChargeException;
import com.musala.task.drones.exceptions.NotFoundException;
import com.musala.task.drones.model.Drone;
import com.musala.task.drones.model.Medication;
import com.musala.task.drones.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DroneController {

    @Autowired
    DroneService droneService;

    @PostMapping("/drone")
    public ResponseEntity<Drone> save(@RequestBody Drone drone){
        Drone savedDrone = droneService.register(drone);
        return new ResponseEntity<> (savedDrone, HttpStatus.OK);
    }

    @GetMapping("/drone/{id}")
    public ResponseEntity<List<Medication>> getMedicationList(@PathVariable("id") long droneId) throws NotFoundException {
        List<Medication> items= droneService.getLoadedItems(droneId);
        return new ResponseEntity<> (items, HttpStatus.OK);
    }

    @PostMapping("/drone/load/{id}")
    public ResponseEntity<Drone> load(@PathVariable("id") long droneId,@RequestBody List<Medication> items) throws ExceedWeightException, NotFoundException, NotEnoughChargeException, InstantiationException {
        Drone savedDrone = droneService.load(droneId,items);
        return new ResponseEntity<> (savedDrone, HttpStatus.OK);
    }

    @GetMapping("/drone")
    public ResponseEntity<List<Drone>> getDronesList() throws NotFoundException {
        List<Drone> drones = droneService.getAvailableDrones();
        return new ResponseEntity<> (drones, HttpStatus.OK);
    }

    @GetMapping("/drone/battery/{id}")
    public ResponseEntity<String> getBatteryLevel(@PathVariable("id") long droneId) throws NotFoundException {
        String batteryCapacity = droneService.getBatteryCapacity(droneId);
        return new ResponseEntity<> (batteryCapacity, HttpStatus.OK);
    }

}

