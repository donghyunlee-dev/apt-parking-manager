package com.windsoft.apartment_parking_manager.data.repository;

import com.windsoft.apartment_parking_manager.data.entity.Bouncer;
import com.windsoft.apartment_parking_manager.data.entity.id.BouncerId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BouncerRepository extends JpaRepository<Bouncer, BouncerId> {
    List<Bouncer> findByAptCode(String aptCode);

}
