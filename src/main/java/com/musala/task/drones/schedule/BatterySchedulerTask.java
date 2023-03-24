package com.musala.task.drones.schedule;

import com.musala.task.drones.model.Drone;
import com.musala.task.drones.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

@Component
public class BatterySchedulerTask {

    @Autowired
    DroneService droneService;

    @Scheduled(fixedDelay = 100000)
    public void scheduleFixedDelayTaskToCheckDronesBattery() throws IOException {
        System.out.println(
                "Fixed delay task - " + System.currentTimeMillis() / 1000);
        List<Drone> droneList = droneService.getAvailableDrones();
        for(Drone drone : droneList){
            String str = "Drone with id "+drone.getId ()+ " and model  "+drone.getModel()+" and state "+drone.getState()
                    +" has battery level = "+drone.getBatteryCapacity()+"\n";
            Files.write(
                    Paths.get("droneHistoryEventLog.txt"),
                    str.getBytes(),
                    StandardOpenOption.APPEND);
        }
    }
}
