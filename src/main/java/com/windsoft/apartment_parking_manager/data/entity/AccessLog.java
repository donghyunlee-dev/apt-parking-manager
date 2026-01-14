package com.windsoft.apartment_parking_manager.data.entity;

import com.windsoft.apartment_parking_manager.data.dto.AccessRequestDto;
import com.windsoft.apartment_parking_manager.data.entity.id.AccessLogId;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@EntityListeners(AuditingEntityListener.class)
@Getter
@IdClass(AccessLogId.class)
@Table(name = "access_log")
@Entity
public class AccessLog {
    @Id
    private long logNo;

    private String aptCode;

    private String bouncerCode;

    private String accessResult;

    private String location;

    private String deviceId;

    @CreatedDate
    @Column(name = "CREATED_AT", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = ZonedDateTime.now(ZoneId.of("UTC"))
                .withZoneSameInstant(ZoneId.of("Asia/Seoul"))
                .toLocalDateTime();
    }

    public static AccessLog toEntity(AccessRequestDto request, Bouncer bouncer) {
        AccessLog log = new AccessLog();
        log.aptCode = request.getAptCode();
        log.bouncerCode = ObjectUtils.isEmpty(bouncer) ? null : bouncer.getBouncerCode();
        log.accessResult = ObjectUtils.isEmpty(bouncer) ? "FAILED" : "SUCCESS";
        log.location = request.getLocation();
        log.deviceId = request.getDeviceId();
        return log;
    }
}
