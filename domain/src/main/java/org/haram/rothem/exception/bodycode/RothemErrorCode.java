package org.haram.rothem.exception.bodycode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RothemErrorCode implements BodyCode {

    ALREADY_EXIST_CALENDAR("RT01", "이미 존재하는 캘린더입니다.", 400),
    NOT_FOUND_RESERVATION("RT02", "예약 정보가 존재하지 않습니다.", 400),
    NOT_CHECKED_ESSENTIAL_TERMS("RT03", "필수 동의 약관이 체크되지 않았습니다.", 400),
    UNAVAILABLE_TIME("RT04", "사용 불가능한 시간입니다.", 400),
    NON_CONSECUTIVE_TIME("RT05", "연속된 시간만 예약 가능합니다.", 400),
    UNAVAILABLE_CALENDAR("RT06", "사용 불가능한 날짜입니다.", 400),
    OVER_LIMIT_TIME("RT07", "예약 가능한 시간을 넘어섰습니다.", 400),
    ALREADY_EXIST_RESERVATION_INFO("RT08", "이미 예약된 내역이 있습니다.", 400),
    ALREADY_EXIST_AMENITY("RT09", "이미 존재하는 편의사항입니다.", 400),
    ALREADY_EXIST_NOTICE("RT10", "이미 존재하는 공지사항입니다.", 400),
    ALREADY_EXIST_POLICY("RT11", "이미 존재하는 정책입니다.", 400),
    ALREADY_EXIST_RESERVATION("RT12", "이미 존재하는 예약입니다.", 400),
    ALREADY_EXIST_ROOM("RT13", "이미 존재하는 스터디룸입니다.", 400),
    ALREADY_EXIST_TIME("RT14", "이미 존재하는 시간입니다.", 400),
    NOT_FOUND_AMENITY("RT15", "편의사항이 존재하지 않습니다.", 400),
    NOT_FOUND_CALENDAR("RT16", "날짜가 존재하지 않습니다.", 400),
    NOT_FOUND_NOTICE("RT17", "공지사항이 존재하지 않습니다.", 400),
    NOT_FOUND_POLICY("RT18", "정책이 존재하지 않습니다.", 400),
    NOT_FOUND_ROOM("RT19", "스터디룸이 존재하지 않습니다.", 400),
    NOT_FOUND_TIME("RT20", "시간이 존재하지 않습니다.", 400),
    ALREADY_EXIST_TIME_STATUS("RT21", "해당 시간 상태는 사용불가입니다.", 400),
    ALREADY_EXIST_RESERVATION_POLICY("RT22", "정책 예약 매칭 테이블이 이미 존재합니다.", 400),
    ILLEGAL_USER("RT23", "올바르지 않은 사용자입니다.", 400),
    ALREADY_EXIST_ROOM_FILE("RT24", "이미 존재하는 스터디룸 파일입니다.", 400),
    ALREADY_EXIST_ROOM_AMENITY("RT25", "이미 존재하는 스터디룸 편의사항 매칭 테이블입니다.", 400),
    ILLEGAL_VALUE_USERNAME("RT26", "userName 값이 존재하지 않습니다.", 400),
    ILLEGAL_VALUE_PHONE_NUM("RT27", "phoneNum 값이 존재하지 않습니다.", 400),
    ILLEGAL_VALUE_CALENDAR_SEQ("RT28", "calendarSeq 값이 존재하지 않습니다.", 400),
    ILLEGAL_VALUE_RESERVATION_POLICY_REQUEST("RT29", "reservationPolicyRequests 값이 존재하지 않습니다.", 400),
    ILLEGAL_VALUE_TIME_REQUEST("RT30", "timeRequests 값이 존재하지 않습니다.", 400),
    ILLEGAL_VALUE_RESERVATION_SEQ("RT31", "reservationSeq 값이 존재하지 않습니다.", 400),
//    ILLEGAL_VALUE_WEEK_STATUS("RT32", "weekStatus 값이 존재하지 않습니다.", 400),
    NO_THUMBNAIL_FILE("RT33", "썸네일 파일이 존재하지 않습니다.", 400),
    FAIL_UPLOAD_FILE("RT34", "파일 업로드 실패입니다.", 400),
    NOT_FOUND_TIME_STATUS("RT35", "해당 시간은 예약되어 있지 않습니다.", 400),
    ILLEGAL_VALUE_ROOM_SEQ("RT36", "roomSeq 값이 존재하지 않습니다.", 400),
    ILLEGAL_VALUE_TITLE("RT37", "title 값이 존재하지 않습니다.", 400),
    ILLEGAL_VALUE_FILE_PATH("RT38", "filePath 값이 존재하지 않습니다.", 400),
    ILLEGAL_VALUE_ROOM_NAME("RT39", "roomName 값이 존재하지 않습니다.", 400),
    ILLEGAL_VALUE_RESERVATION_STATUS("RT40", "status 값이 존재하지 않습니다.", 400),
    ;

    private final String code;

    private final String message;

    private final Integer status;

}
