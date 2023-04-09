package com.example.rodem.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Reservation {
    private Long id;
    private Room room;
    private Long userId;
    private boolean isAccepted; // 예약 처리 여부 (관리자가 승인 해야 true)

    @Builder
    public Reservation(Long id, Room room, Long userId, boolean isAccepted) {
        this.id = id;
        this.room = room;
        this.userId = userId;
        this.isAccepted = isAccepted;
    }
}
