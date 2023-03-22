package com.musala.task.drones.repo;

import com.musala.task.drones.model.Drone;
import org.springframework.data.repository.CrudRepository;

public interface DroneRepository extends CrudRepository<Drone, Long> {

}
