package com.windsoft.apartment_parking_manager.data.entity.id;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApartmentId implements Serializable {

    public static final long serialVersionUID = 1L;

    @Column(name = "apt_code")
    String aptCode;
}
