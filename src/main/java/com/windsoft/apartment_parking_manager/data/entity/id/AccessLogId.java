package com.windsoft.apartment_parking_manager.data.entity.id;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccessLogId implements Serializable {

    public static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private long logNo;
}
