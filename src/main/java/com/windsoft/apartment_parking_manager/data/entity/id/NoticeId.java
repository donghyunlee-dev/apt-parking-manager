package com.windsoft.apartment_parking_manager.data.entity.id;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@SequenceGenerator(
        name = "notice_seq",
        sequenceName = "notice_seq",
        allocationSize = 1
)
@EqualsAndHashCode
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NoticeId implements Serializable {

    private static final long serialVersionUID = 1L;

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "notice_seq"
    )
    private long id;
}
