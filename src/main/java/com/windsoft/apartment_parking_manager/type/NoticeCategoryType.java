package com.windsoft.apartment_parking_manager.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum NoticeCategoryType {

    INFO("일반 안내"),
    SYSTEM("시스템 공지사항"),
    UPDATE("업데이트 안내"),
    PAYMENT("결제 안내")
    ;

    private final String name;

    public String getCode() {
        return this.name();
    }
}
