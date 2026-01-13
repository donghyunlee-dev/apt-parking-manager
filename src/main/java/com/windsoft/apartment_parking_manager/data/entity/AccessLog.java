package com.windsoft.apartment_parking_manager.data.entity;

import com.windsoft.apartment_parking_manager.data.entity.id.AccessLogId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.LocalDateTime;

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

    private LocalDateTime createdAt;



}
