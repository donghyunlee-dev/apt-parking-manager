package com.windsoft.apartment_parking_manager.data.entity;


import com.windsoft.apartment_parking_manager.data.dto.BouncerRequestDto;
import com.windsoft.apartment_parking_manager.data.entity.id.BouncerId;
import com.windsoft.apartment_parking_manager.util.AES256GcmConverter;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    public static Bouncer toEntity(BouncerRequestDto.Registration admin) {
        BCryptPasswordEncoder encorder = new BCryptPasswordEncoder();

        Bouncer bouncer = new Bouncer();

        bouncer.aptCode = admin.getAptCode();
        bouncer.bouncerCode = "B000";
        bouncer.bouncerName = "관리자";
        bouncer.finNo = encorder.encode(admin.getFinNo());
        bouncer.grade = "A";
        bouncer.deviceId = admin.getDeviceId();
        bouncer.used = "Y";
        return bouncer;
    }

}
