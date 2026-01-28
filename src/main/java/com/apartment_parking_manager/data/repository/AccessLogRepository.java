package com.windsoft.apartment_parking_manager.data.repository;

import com.windsoft.apartment_parking_manager.data.entity.AccessLog;
import com.windsoft.apartment_parking_manager.data.entity.id.AccessLogId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessLogRepository extends JpaRepository<AccessLog, AccessLogId> {
}
