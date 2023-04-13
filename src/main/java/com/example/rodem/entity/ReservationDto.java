package com.example.rodem.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationDto {

    private Long roomId;                // 예약한 방 정보
    private Long userId;                // 예약한 사용자 ID
    private Long personNumber;          // 방을 이용하는 사람 수
    private String reservedTime;        // 요청된 시간
    private String participateProgram;  // 참여중인 프로젝트


    @Builder
    public ReservationDto(Long roomId, Long userId, Long personNumber, String participateProgram) {
        this.roomId = roomId;
        this.userId = userId;
        this.personNumber = personNumber;
        this.participateProgram = participateProgram;
    }
}
