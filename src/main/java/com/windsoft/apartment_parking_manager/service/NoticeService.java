package com.windsoft.apartment_parking_manager.service;

import com.windsoft.apartment_parking_manager.data.dto.NoticeResponseDto;

import java.util.List;

public interface NoticeService {

    List<NoticeResponseDto.NoticeView> getRecentNotice();
}
