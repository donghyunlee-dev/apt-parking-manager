package com.windsoft.apartment_parking_manager.data.dto;

import com.windsoft.apartment_parking_manager.type.NoticeCategoryType;
import com.windsoft.apartment_parking_manager.type.NoticeType;
import lombok.Getter;

@Getter
public class CodeName {

    private String code;
    private String name;

    public CodeName(final String code, final String name) {
        this.code = code;
        this.name = name;
    }

    public static CodeName valueOf(final NoticeCategoryType type) {
        return new CodeName(type.getCode(), type.getName());
    }

    public static CodeName valueOf(final NoticeType type) {
        return new CodeName(type.getCode(), type.getName());
    }
}
