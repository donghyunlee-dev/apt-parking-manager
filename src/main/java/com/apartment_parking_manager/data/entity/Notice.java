package com.windsoft.apartment_parking_manager.data.entity;

import com.windsoft.apartment_parking_manager.data.entity.id.NoticeId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@IdClass(NoticeId.class)
@Table
@Entity
public class Notice extends BaseEntity {
    @Id
    private long id;

    private String category;

    private String type;

    private String subject;

    private String content;

    private LocalDateTime openAt;

    private LocalDateTime closeAt;

    private String aptCode;

    private long refId;

    private boolean used;
}
