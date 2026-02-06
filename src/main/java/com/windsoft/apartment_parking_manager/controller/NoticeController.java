package com.windsoft.apartment_parking_manager.controller;

import com.windsoft.apartment_parking_manager.data.dto.BaseResponseDto;
import com.windsoft.apartment_parking_manager.data.dto.NoticeResponseDto;
import com.windsoft.apartment_parking_manager.data.dto.RequestContext;
import com.windsoft.apartment_parking_manager.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/notice")
@RestController
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping
    public ResponseEntity<BaseResponseDto<?>> retrieveNotice(RequestContext context) {

        if (context == null) {
            return ResponseEntity.badRequest().build();
        }

        List<NoticeResponseDto.NoticeView> noticeView = noticeService.getRecentNotice();

        return ResponseEntity.ok(new BaseResponseDto<>(noticeView));
    }
}
