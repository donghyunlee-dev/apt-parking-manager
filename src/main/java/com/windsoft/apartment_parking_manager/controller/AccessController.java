package com.windsoft.apartment_parking_manager.controller;

import com.windsoft.apartment_parking_manager.data.dto.AccessRequestDto;
import com.windsoft.apartment_parking_manager.data.dto.AccessResponseDto;
import com.windsoft.apartment_parking_manager.data.dto.AdminRequestDto;
import com.windsoft.apartment_parking_manager.data.dto.AdminResponseDto;
import com.windsoft.apartment_parking_manager.service.AccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class AccessController {

    private final AccessService accessService;

    @PostMapping("/access")
    public ResponseEntity<AccessResponseDto> validateAccess(@RequestBody AccessRequestDto request) {
        AccessResponseDto response = accessService.validateAccess(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/views/sign")
    public ResponseEntity<AdminResponseDto> signIn(@RequestBody AdminRequestDto request) {

        AdminResponseDto response = accessService.signIn(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
