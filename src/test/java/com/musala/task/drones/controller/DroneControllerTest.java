package com.musala.task.drones.controller;

import com.musala.task.drones.model.Drone;
import com.musala.task.drones.model.Medication;
import com.musala.task.drones.model.Model;
import com.musala.task.drones.model.State;
import com.musala.task.drones.service.DroneServiceImp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DroneController.class)
public class DroneControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DroneServiceImp droneService;

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
    public void givenDrones_whenSave_thenReturnJsonObject()
            throws Exception {

        given(droneService.register(Mockito.any())).willReturn(drone);

        this.mvc.perform(post("/drone").content ("{\n" +
                        "    \"model\":\"LIGHT_WEIGHT\",\n" +
                        "    \"weight\":0,\n" +
                        "    \"batteryCapacity\":50,\n" +
                        "    \"state\":\"IDLE\"\n" +
                        "}").contentType("application/json"))
                .andExpect(status().isOk()).andExpect(content()
                        .contentType("application/json"));
    }

    @Test
    public void givenDroneId_whenGetMedicationList_thenReturnListOfMedications()
            throws Exception {

        given(droneService.getLoadedItems(1L)).willReturn(medications);
        mvc.perform(MockMvcRequestBuilders.get("/drone/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().
                        bytes("[{\"id\":1,\"name\":null,\"weight\":2.5,\"code\":\"AAEDS1\",\"drone\":null,\"image\":null}]".getBytes ()))
                .andReturn();


    }

    @Test
    public void whenGetDrones_thenReturnListOfDrones()
            throws Exception {

        given(droneService.getAvailableDrones()).willReturn(Arrays.asList(drone));
        mvc.perform(MockMvcRequestBuilders.get("/drone"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().
                        bytes("[{\"id\":1,\"model\":\"LIGHT_WEIGHT\",\"weight\":0.0,\"batteryCapacity\":100,\"state\":\"DELIVERED\"}]".getBytes ()))
                .andReturn();


    }

    @Test
    public void givenDroneId_whenGetDroneBattery_thenReturnBattery()
            throws Exception {

        given(droneService.getBatteryCapacity(1L)).willReturn("100 %");
        mvc.perform(MockMvcRequestBuilders.get("/drone/battery/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().
                        bytes("100 %".getBytes ()))
                .andReturn();

    }

}
