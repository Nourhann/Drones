package com.musala.task.drones.service;

import com.musala.task.drones.exceptions.NotFoundException;
import com.musala.task.drones.model.Drone;
import com.musala.task.drones.model.Medication;
import com.musala.task.drones.model.Model;
import com.musala.task.drones.model.State;
import com.musala.task.drones.repo.DroneRepository;
import com.musala.task.drones.repo.MedicationRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DroneServiceTest {

    @InjectMocks
    private DroneServiceImp droneService;

    @Mock
    private DroneRepository droneRepository;

    @Mock
    private MedicationRepository medicationRepository;

    private Drone drone;

    private List<Medication> medications;

    @Before
    public void init(){
        drone = new Drone();
        drone.setId(1L);
        drone.setBatteryCapacity (100);
        drone.setModel (Model.LIGHT_WEIGHT);
        drone.setState (State.DELIVERED);
        drone.setWeight(0);

        Medication medication = new Medication ();
        medication.setId(1L);
        medication.setWeight(2.5);
        medication.setCode("AAEDS1");

        medications = new ArrayList<> ();
        medications.add(medication);
    }

    @Test
    public void testRegister(){
       when(droneRepository.save(Mockito.any())).thenReturn(drone);
        Drone d = droneService.register(drone);
        Assert.assertEquals(100,d.getBatteryCapacity());
    }

    @Test
    public void testGetBatteryCapacity() throws NotFoundException {
        when(droneRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(drone));
        String battery = droneService.getBatteryCapacity(drone.getId());
        Assert.assertEquals("100 %",battery);
    }

    @Test
    public void testGetAvailableDrones() {
        when(droneRepository.findAll()).thenReturn(Arrays.asList(drone));
        List<Drone> droneList= droneService.getAvailableDrones();
        Assert.assertEquals(1,droneList.size());
    }

    @Test
    public void testGetLoadedItems() throws NotFoundException {
        when(droneRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(drone));
        when(medicationRepository.findByDrone(Mockito.any())).thenReturn(medications);
        List<Medication> meds = droneService.getLoadedItems(drone.getId());
        Assert.assertEquals(1,meds.size());
    }
}
