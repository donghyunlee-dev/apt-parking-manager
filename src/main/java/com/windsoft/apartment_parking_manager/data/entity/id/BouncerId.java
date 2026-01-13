package com.windsoft.apartment_parking_manager.data.entity.id;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BouncerId implements Serializable {

    public static final long serialVersionUID = 1L;

    private String aptCode;

    private String bouncerCode;

}
