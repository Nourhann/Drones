package com.musala.task.drones.repo;

import com.musala.task.drones.model.Drone;
import com.musala.task.drones.model.Medication;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MedicationRepository extends CrudRepository<Medication, Long> {

    List<Medication> findByDrone(Drone drone);

}
