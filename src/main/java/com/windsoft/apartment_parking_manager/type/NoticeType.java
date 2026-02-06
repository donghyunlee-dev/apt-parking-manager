package com.windsoft.apartment_parking_manager.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum NoticeType {

    A("일반"),
    I("중요"),
    E("긴급")
    ;
    private final String name;

    public String getCode() {
        return this.name();
    }
}
