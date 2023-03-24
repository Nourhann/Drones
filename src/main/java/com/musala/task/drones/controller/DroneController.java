package com.musala.task.drones.controller;

import com.musala.task.drones.exceptions.ExceedWeightException;
import com.musala.task.drones.exceptions.NotEnoughChargeException;
import com.musala.task.drones.exceptions.NotFoundException;
import com.musala.task.drones.model.Drone;
import com.musala.task.drones.model.Medication;
import com.musala.task.drones.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/drone")
public class DroneController {

    @Autowired
    DroneService droneService;

    @PostMapping
    public ResponseEntity<Drone> save(@RequestBody Drone drone){
        Drone savedDrone = droneService.register(drone);
        return new ResponseEntity<> (savedDrone, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Medication>> getMedicationList(@PathVariable("id") long droneId) throws NotFoundException {
        List<Medication> items= droneService.getLoadedItems(droneId);
        return new ResponseEntity<> (items, HttpStatus.OK);
    }

    @RequestMapping(path = "/load/{id}", method = POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity load(@PathVariable("id") long droneId,@RequestPart("medications") List<Medication> items,
                                      @RequestPart("images") List<MultipartFile> files) throws ExceedWeightException, NotFoundException, NotEnoughChargeException, InstantiationException, IOException {
        droneService.load(droneId,items,files);
        return new ResponseEntity<> (HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Drone>> getDronesList() {
        List<Drone> drones = droneService.getAvailableDrones();
        return new ResponseEntity<> (drones, HttpStatus.OK);
    }

    @GetMapping("/battery/{id}")
    public ResponseEntity<String> getBatteryLevel(@PathVariable("id") long droneId) throws NotFoundException {
        String batteryCapacity = droneService.getBatteryCapacity(droneId);
        return new ResponseEntity<> (batteryCapacity, HttpStatus.OK);
    }

}

