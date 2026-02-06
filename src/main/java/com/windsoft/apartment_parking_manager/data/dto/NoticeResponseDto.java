package com.windsoft.apartment_parking_manager.data.dto;

import com.windsoft.apartment_parking_manager.data.entity.Notice;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


public class NoticeResponseDto {

    @Builder
    @Getter
    public static class RecentNotice {

        private long id;

        private String categoryCode;
        private String categoryName;

        private String type;
        private String typeName;

        private String subject;
    }

    @Builder
    @Getter
    public static class NoticeView {

        private long id;

        private CodeName category;

        private CodeName type;

        private String subject;

        private String content;

        public static NoticeView toDto(Notice notice) {

            return NoticeView
                    .builder()
                    .id(notice.getId())
                    .category(CodeName.valueOf(notice.getCategory()))
                    .type(CodeName.valueOf(notice.getType()))
                    .subject(notice.getSubject())
                    .content(notice.getContent())
                    .build();
        }
    }
}
