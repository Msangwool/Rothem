package org.haram.rothem.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.haram.rothem.data.dto.user.request.ReservationDeleteRequest;
import org.haram.rothem.data.dto.user.request.ReservationRequest;
import org.haram.rothem.data.dto.user.response.*;
import org.haram.rothem.data.model.ResponseWrapper;
import org.haram.rothem.service.UiDelegator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.haram.rothem.core.bodycode.SuccessCode.SUCCESS;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/rothem")
public class RothemUserController {

    private final UiDelegator uiDelegator;

    @GetMapping("/main/{userId}")
    public ResponseEntity<ResponseWrapper<MainInfoResponse>> getMainInfo(@PathVariable String userId) {
        // room 조회
        // notice 조회
        // reservation(상태 1 or 2 있는지) 조회
        return ResponseWrapper.toResponse(SUCCESS, uiDelegator.getMainInfo(userId));
    }

    @GetMapping("/rooms/{roomSeq}")
    public ResponseEntity<ResponseWrapper<RoomDetailResponse>> getRoomInfo(@PathVariable Long roomSeq) {
        // room 조회
        // room_file 조회
        // room_amenity 조회
        return ResponseWrapper.toResponse(SUCCESS, uiDelegator.getRoomInfo(roomSeq));
    }

    @GetMapping("/rooms/{roomSeq}/reservations")
    public ResponseEntity<ResponseWrapper<RoomReservationTimeResponse>> getReservationTime(@PathVariable Long roomSeq) {
        // room 조회
        // policy 조회
        // calendar 조회
        // time + time_status 조회
        return ResponseWrapper.toResponse(SUCCESS, uiDelegator.getReservationTime(roomSeq));
    }

    @PostMapping("/rooms/{roomSeq}/reservations")
    public ResponseEntity<ResponseWrapper<Void>> reserve(
            @PathVariable Long roomSeq,
            @RequestBody ReservationRequest reservationRequest) {
        // reservation 생성
        // time_status 생성
        // reservation_policy 생성
        reservationRequest.validate();
        reservationRequest.setRoomSeq(roomSeq);
        uiDelegator.reserve(reservationRequest);
        return ResponseWrapper.toResponse(SUCCESS);
    }

    @GetMapping("/reservations/{userId}")
    public ResponseEntity<ResponseWrapper<ReservationInfoResponse>> getReservationInfo(
            @PathVariable String userId) {
        // reservation 조회
        // time_status 조회
        // room 조회
        // calendar 조회
        // time 조회
        return ResponseWrapper.toResponse(SUCCESS, uiDelegator.getReservationInfo(userId));
    }

    @DeleteMapping("/reservations")
    public ResponseEntity<ResponseWrapper<Void>> cancelReservation(
            @RequestBody ReservationDeleteRequest reservationDeleteRequest) {
        // time_status 상태변경
        // reservation 상태변경
        reservationDeleteRequest.validate();
        uiDelegator.cancelReservation(reservationDeleteRequest);
        return ResponseWrapper.toResponse(SUCCESS);
    }

    @GetMapping("/notices/{noticeSeq}")
    public ResponseEntity<ResponseWrapper<NoticeDetailResponse>> getNoticeInfo(@PathVariable Long noticeSeq) {
        // notice 조회
        // notice_file 조회
        return ResponseWrapper.toResponse(SUCCESS, uiDelegator.getNoticeInfo(noticeSeq));
    }

}
