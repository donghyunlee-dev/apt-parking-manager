package com.windsoft.apartment_parking_manager.data.entity;


import com.windsoft.apartment_parking_manager.data.entity.id.BouncerId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@IdClass(BouncerId.class)
@Table(name = "bouncer")
@Entity
public class Bouncer extends BaseEntity {

    @Id
    private String aptCode;

    @Id
    private String bouncerCode;

    private String bouncerName;

    private String finNo;

    private String grade;

    private String deviceId;

    private String used;

}
