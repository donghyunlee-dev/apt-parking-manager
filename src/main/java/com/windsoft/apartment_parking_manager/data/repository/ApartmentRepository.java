package com.windsoft.apartment_parking_manager.data.repository;

import com.windsoft.apartment_parking_manager.data.entity.Apartment;
import com.windsoft.apartment_parking_manager.data.entity.id.ApartmentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ApartmentRepository extends JpaRepository<Apartment, ApartmentId> {

    Apartment findByAddressAndStatus(String address, String status);

    Apartment findByAptNameAndStatus(String aptName, String status);

    @Query("SELECT MAX(a.aptCode) FROM Apartment a")
    String getMaxApartmentCode();
}
