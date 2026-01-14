package com.windsoft.apartment_parking_manager.service.impl;

import com.windsoft.apartment_parking_manager.data.dto.AccessRequestDto;
import com.windsoft.apartment_parking_manager.data.dto.AccessResponseDto;
import com.windsoft.apartment_parking_manager.data.entity.Bouncer;
import com.windsoft.apartment_parking_manager.data.repository.AccessLogRepository;
import com.windsoft.apartment_parking_manager.data.repository.BouncerRepository;
import com.windsoft.apartment_parking_manager.service.AccessService;
import com.windsoft.apartment_parking_manager.util.CustomObjectUtils;
import jakarta.persistence.Access;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AccessServiceImpl implements AccessService {

    private final AccessLogRepository accessLogRepository;
    private final BouncerRepository bouncerRepository;

    @Override
    public AccessResponseDto validateAccess(AccessRequestDto requestDto) {

        List<Bouncer> bouncers = bouncerRepository.findByAptCodeAndUsed(requestDto.getAptCode(), "Y");

        if (bouncers.isEmpty()) {
            return AccessResponseDto.noData();
        }
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        Bouncer findBouncer = null;

        for (Bouncer bouncer : bouncers) {
            if (encoder.matches(requestDto.getFinCode(), bouncer.getFinNo())) {
                findBouncer = CustomObjectUtils.deepCopy(bouncer, Bouncer.class);
            }
        }

        if (ObjectUtils.isEmpty(findBouncer)) {
            return AccessResponseDto.noData();
        }

        return AccessResponseDto.setData(findBouncer);
    }
}
