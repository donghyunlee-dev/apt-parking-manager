package com.windsoft.apartment_parking_manager.data.entity;

import com.windsoft.apartment_parking_manager.data.entity.id.NoticeId;
import com.windsoft.apartment_parking_manager.type.NoticeCategoryType;
import com.windsoft.apartment_parking_manager.type.NoticeType;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@IdClass(NoticeId.class)
@Table
@Entity
public class Notice extends BaseEntity {
    @Id
    private long id;

    @Enumerated(EnumType.STRING)
    private NoticeCategoryType category;

    @Enumerated(EnumType.STRING)
    private NoticeType type;

    private String subject;

    private String content;

    private LocalDateTime openAt;

    private LocalDateTime closeAt;

    private String aptCode;

    private long refId;

    private boolean used;
}
