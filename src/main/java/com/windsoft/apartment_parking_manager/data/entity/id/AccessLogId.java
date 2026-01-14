package com.windsoft.apartment_parking_manager.data.entity.id;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
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

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "access_log_seq"
    )
    @SequenceGenerator(
            name = "access_log_seq",
            sequenceName = "access_log_seq",
            allocationSize = 1
    )
    private long logNo;
}
