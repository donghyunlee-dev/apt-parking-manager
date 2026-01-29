package com.windsoft.apartment_parking_manager.service;

import com.windsoft.apartment_parking_manager.data.dto.*;

import java.time.LocalDate;

public interface VehicleService {

    /**
     * 주차 차량 검사
     * 차량 번호로 차량 정보를 조회
     * @param request 차량 번호
     * @return 입주민, 방문 , 미등록 차량 정보
     */
    VehicleResponseDto.ParkingInfo findParkingVehicle(VehicleRequestDto.VehiclePlateRequest request);

    /***
     * 주차 차량 확인 로그 저장
     * @param request 차량 번호
     * @param vehicleInfo 확인된 차량 정보
     */
    void saveParkingVehicleLog(VehicleRequestDto.VehiclePlateRequest request, VehicleResponseDto.ParkingInfo vehicleInfo);

    /**
     * 입주민 차량 등록
     * @param request 입주민 차량, 아파트 정보
     * @return 등록된 차량 정보
     */
    VehicleResponseDto.VehicleInfo saveResidentVehicle(VehicleRequestDto.RegistrationRequest request);

    /**
     * 입주민 차량 정보 수정
     * @param vehicleNo 차량 번호 (key)
     * @param request 변경 요청하는 입주민 차량 정보, 아파트 정보
     * @return 수정된 입주민 차량 정보
     */
    VehicleResponseDto.VehicleInfo updateResidentVehicle(String vehicleNo, VehicleRequestDto.ModificationRequest request);

    /**
     * 입주민 차량 등록 여부 변경
     * 차량을 바꾸거나 이사를 갔을 경우 비활성화 처리
     * @param request 입주민 차량 번호
     * @return
     */
    VehicleResponseDto.VehicleInfo changeUsageResidentVehicle(VehicleRequestDto.VehiclePlateRequest request);

    /**
     * 입주민 차량 정보 삭제
     * 잘못된 차량 번호를 입력한 경우 삭제 처리
     * 차량 번호가 키이기 때문에 그 외 정보는 수정으로 처리
     * @param request
     */
    void deleteResidentVehicle(VehicleRequestDto.VehiclePlateRequest request);

    /**
     * 방문자 차량 정보 등록
     * @param request 방문자 차량 정보, 방문 기간 정보
     * @return 등록된 방문자 차량 정보
     */
    VisitorVehicleResponseDto.VisitorVehicleInfo saveVisitVehicle(VisitorVehicleRequestDto.RegistrationRequest request);

    /**
     * 방문자 차량 정보 수정
     * @param vehicleNo 방문자 차량 번호
     * @param request 변경 요청하는 방문자 차량 정보, 방문 기간
     * @return 수정된 방문자 차량 정보
     */
    VisitorVehicleResponseDto.VisitorVehicleInfo updateVisitVehicle(String vehicleNo, VisitorVehicleRequestDto.ModificationRequest request);

    /**
     * 방문자 차량 정보 삭제
     * 방문자 차량번호가 잘못 입력되었을 때 삭제 처리
     * @param context 요청 헤더 정보
     * @param vehicleNo 차량 번호
     * @param visitDate 방문 일자
     */
    void deleteVisitorVehicle(RequestContext context, String vehicleNo, LocalDate visitDate);

    /**
     * 차량 정보 조회
     * 차량 번호로 입주민, 방문, 미등록 차량 정보 검색
     * @param request
     * @return
     */
    VehicleResponseDto.VehicleInfo getVehicle(VehicleRequestDto.VehiclePlateRequest request);
}
