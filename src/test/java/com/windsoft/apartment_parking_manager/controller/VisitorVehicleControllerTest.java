package com.windsoft.apartment_parking_manager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.windsoft.apartment_parking_manager.TestConfig;
import com.windsoft.apartment_parking_manager.data.dto.RequestContext;
import com.windsoft.apartment_parking_manager.data.dto.VisitorVehicleRequestDto;
import com.windsoft.apartment_parking_manager.data.dto.VisitorVehicleResponseDto;
import com.windsoft.apartment_parking_manager.data.entity.VisitorVehicle;
import com.windsoft.apartment_parking_manager.service.VehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Import(TestConfig.class)
public class VisitorVehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private VehicleService visitorVehicleService;

    private VisitorVehicleRequestDto.RegistrationRequest registrationRequest;
    private VisitorVehicleRequestDto.ModificationRequest modificationRequest;
    private VisitorVehicleResponseDto.VisitorVehicleInfo visitorVehicleInfo;

    @BeforeEach
    void setUp() {
        RequestContext context = new RequestContext("testAptCode", "testBouncerCode", "testDeviceId", "testAppVersion");

        registrationRequest = new VisitorVehicleRequestDto.RegistrationRequest(
                "123가4567", "101", "1001", "010-1234-5678",
                LocalDate.of(2026, 1, 1), LocalTime.of(10, 0), LocalDate.of(2026, 1, 1),
                "test memo"
        );

        modificationRequest = new VisitorVehicleRequestDto.ModificationRequest(
                "101", "1001", "010-8765-4321",LocalDate.of(2026, 1, 5), LocalTime.of(10, 0),
                LocalDate.of(2026, 1, 5), "updated memo"
        );

        VisitorVehicleRequestDto.RegistrationRequest testRequest = new VisitorVehicleRequestDto.RegistrationRequest("123가4567", "101", "1001", "010-1234-5678",
                LocalDate.of(2026, 1, 1), LocalTime.of(10, 0), LocalDate.of(2026, 1, 1),
                "test memo");

        VisitorVehicle visitorVehicle = VisitorVehicle.setData(testRequest);

        visitorVehicleInfo = VisitorVehicleResponseDto.VisitorVehicleInfo.setData(visitorVehicle);
    }

    @Test
    void registerVisitVehicle_shouldReturnCreated() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        when(visitorVehicleService.saveVisitVehicle(any(VisitorVehicleRequestDto.RegistrationRequest.class)))
                .thenReturn(visitorVehicleInfo);

        mockMvc.perform(post("/api/vehicle/visitors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registrationRequest))
                        .header("X-Apt-Code", "testAptCode")
                        .header("X-Bouncer-Code", "testBouncerCode")
                        .header("X-Device-Id", "testDeviceId")
                        .header("X-App-Version", "testAppVersion"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.payload.vehicleNo").value("123가4567"));
    }

    @Test
    void modifyVisitVehicle_shouldReturnOk() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        when(visitorVehicleService.updateVisitVehicle(any(String.class), any(VisitorVehicleRequestDto.ModificationRequest.class)))
                .thenReturn(visitorVehicleInfo);

        mockMvc.perform(put("/api/vehicles/visitors/{vehicle_no}", "123가4567")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(modificationRequest))
                        .header("X-Apt-Code", "testAptCode")
                        .header("X-Bouncer-Code", "testBouncerCode")
                        .header("X-Device-Id", "testDeviceId")
                        .header("X-App-Version", "testAppVersion"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.payload.vehicleNo").value("123가4567"));
    }

    @Test
    void removeVisitVehicle_shouldReturnOk() throws Exception {
        doNothing().when(visitorVehicleService).deleteVisitorVehicle(any(RequestContext.class), any(String.class), any(LocalDate.class));

        mockMvc.perform(delete("/api/vehicle/visit/{vehicle_no}/{visit_date}", "123가4567", "2026-12-12")
                        .header("X-Apt-Code", "testAptCode")
                        .header("X-Bouncer-Code", "testBouncerCode")
                        .header("X-Device-Id", "testDeviceId")
                        .header("X-App-Version", "testAppVersion"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("삭제되었습니다."));
    }
}
