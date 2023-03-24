package com.musala.task.drones.service;

import com.musala.task.drones.exceptions.ExceedWeightException;
import com.musala.task.drones.exceptions.NotEnoughChargeException;
import com.musala.task.drones.exceptions.NotFoundException;
import com.musala.task.drones.model.Drone;
import com.musala.task.drones.model.Medication;
import com.musala.task.drones.repo.DroneRepository;
import com.musala.task.drones.repo.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DroneServiceImp implements DroneService{

    private static int DRONE_MAX_WEIGHT= 500;
    private static int DRONE_MIN_BATTERY_ALLOWED= 25;
    @Autowired
    DroneRepository droneRepository;
    @Autowired
    MedicationRepository medicationRepository;

    @Override
    public Drone register(Drone drone) {
        return droneRepository.save(drone);
    }

    @Override
    public void load(long droneId, List<Medication> items, List<MultipartFile> files) throws ExceedWeightException, NotFoundException, NotEnoughChargeException, IOException {
        double totalWeight=0;
        Optional<Drone> drone = droneRepository.findById (droneId);
        if(drone.isEmpty()){
            throw new NotFoundException("Drone with id = "+ droneId + " not found");
        }
        if(drone.get().getBatteryCapacity() < DRONE_MIN_BATTERY_ALLOWED){
            throw new NotEnoughChargeException ("You have to charge the drone first it is below 25%");
        }
        for (Medication medication: items) {
            totalWeight+=medication.getWeight();
        }
        if(totalWeight <= DRONE_MAX_WEIGHT){
            for(MultipartFile file : files){
                Medication med = items.stream()
                        .filter(medication -> file.getOriginalFilename().substring(0,file.getOriginalFilename().indexOf('.'))
                                .contains(medication.getName()))
                        .findAny()
                        .orElse(null);
                if(med != null){
                    med.setImage(file.getBytes());
                    med.setDrone(drone.get());
                }
            }
            medicationRepository.saveAll(items);
        }
        else{
           throw new ExceedWeightException("Medication List Weight Exceed The Drone Max Weight");
        }
    }

    @Override
    public List<Medication> getLoadedItems(long droneId) throws NotFoundException {
        Optional<Drone> drone = droneRepository.findById (droneId);
        if(drone.isEmpty()){
            throw new NotFoundException("Drone with id = "+ droneId + " not found");
        }
        List<Medication> medications = medicationRepository.findByDrone(drone.get());
        return medications;
    }

    @Override
    public List<Drone> getAvailableDrones() {
        Iterable<Drone> drones = droneRepository.findAll();
        List<Drone> result = new ArrayList<>();
        for (Drone drone : drones) {
            result.add(drone);
        }
        return result;
    }

    @Override
    public String getBatteryCapacity(long droneId) throws NotFoundException {
        Optional<Drone> drone = droneRepository.findById(droneId);
        if(drone.isEmpty()){
            throw new NotFoundException("Drone with id = "+ droneId + " not found");
        }
        return drone.get().getBatteryCapacity()+" %";
    }
}
