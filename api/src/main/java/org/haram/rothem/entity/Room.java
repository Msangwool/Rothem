package org.haram.rothem.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Room {
    private Long id;                    // 방 ID
    private String explanation;         // 방 설명 (안내 사항)
    private Long availablePersonnel;    // 사용 가능한 인원
    private Boolean isReserved;         // 예약 가능 여부
    private String environment;         // 방 환경 (대여실에 있는 물품 내역)
    private String expiredTime;         // 만료되는 시간

    @Builder
    public Room(Long id, String explanation, Long availablePersonnel, Boolean isReserved, String environment, String expiredTime) {
        this.id = id;
        this.explanation = explanation;
        this.availablePersonnel = availablePersonnel;
        this.isReserved = isReserved;
        this.environment = environment;
        this.expiredTime = expiredTime;
    }
}
