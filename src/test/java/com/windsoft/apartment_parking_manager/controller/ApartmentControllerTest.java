package com.windsoft.apartment_parking_manager.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.windsoft.apartment_parking_manager.TestConfig;
import com.windsoft.apartment_parking_manager.data.dto.ApartmentRequestDto;
import com.windsoft.apartment_parking_manager.service.ApartmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Import(TestConfig.class)
class ApartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateApartment() throws Exception {

        ApartmentRequestDto.Registration registration = ApartmentRequestDto.Registration
                .builder()
                .aptName("테스트아파트")
                .address("인천 남동구 만수동 844-1")
                .building(1)
                .resident(1)
                .build();
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(
                    post("/api/apartment").contentType("application/json").content(mapper.writeValueAsString(registration))
                )
                .andExpect(status().isOk());
    }
}