package com.windsoft.apartment_parking_manager.service.impl;

import com.windsoft.apartment_parking_manager.data.dto.NoticeResponseDto;
import com.windsoft.apartment_parking_manager.data.entity.Notice;
import com.windsoft.apartment_parking_manager.data.repository.NoticeRepository;
import com.windsoft.apartment_parking_manager.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    @Override
    public List<NoticeResponseDto.NoticeView> getRecentNotice() {
        LocalDateTime now = LocalDateTime.now();
        List<Notice> noticeList = noticeRepository.findByUsedAndOpenAtLessThanEqualAndCloseAtGreaterThanEqual(true, now, now);

        return noticeList.stream().map(NoticeResponseDto.NoticeView::toDto).toList();
    }
}
