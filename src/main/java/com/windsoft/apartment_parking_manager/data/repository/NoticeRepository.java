package com.windsoft.apartment_parking_manager.data.repository;

import com.windsoft.apartment_parking_manager.data.entity.Notice;
import com.windsoft.apartment_parking_manager.data.entity.id.NoticeId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, NoticeId> {
    List<Notice> findByUsedAndOpenAtLessThanEqualAndCloseAtGreaterThanEqual(boolean used, LocalDateTime openDateTime, LocalDateTime closeDateTime);

}
