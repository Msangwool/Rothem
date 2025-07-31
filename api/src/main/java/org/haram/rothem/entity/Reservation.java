package org.haram.rothem.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Reservation {
    private Long id;                    // 예약 번호
    private Long roomId;                // 예약한 방 정보
    private Long userId;                // 예약한 사용자 ID
    private Long personNumber;          // 방을 이용하는 사람 수
    private String reservedTime;        // 요청된 시간
    private String approvedTime;        // 승인된 시간
    private String expiredTime;         // 만료되는 시간
    private Boolean isAccepted;         // 예약 처리 여부 (관리자가 승인 해야 true)
    private Long availableExtend;       // 연장 가능 횟수
    private String participateProgram;  // 참여중인 프로젝트

    @Builder
    public Reservation(Long id, Long roomId, Long userId, Long personNumber, String reservedTime, String approvedTime, String expiredTime, Boolean isAccepted, Long availableExtend, String participateProgram) {
        this.id = id;
        this.roomId = roomId;
        this.userId = userId;
        this.personNumber = personNumber;
        this.reservedTime = reservedTime;
        this.approvedTime = approvedTime;
        this.expiredTime = expiredTime;
        this.isAccepted = isAccepted;
        this.availableExtend = availableExtend;
        this.participateProgram = participateProgram;
    }
}
