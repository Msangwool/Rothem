package org.haram.rothem.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.haram.rothem.data.dto.notice.NoticeDetailSpaceEntity;
import org.haram.rothem.data.dto.notice.NoticeResponse;
import org.haram.rothem.data.dto.user.request.ReservationDeleteRequest;
import org.haram.rothem.data.dto.user.request.ReservationPolicyRequest;
import org.haram.rothem.data.dto.user.request.ReservationRequest;
import org.haram.rothem.data.dto.user.request.TimeStatusRequest;
import org.haram.rothem.data.dto.user.response.*;
import org.haram.rothem.data.entity.*;
import org.haram.rothem.data.entity.Calendar;
import org.haram.rothem.data.type.ReservationStatus;
import org.haram.rothem.data.type.ReservationType;
import org.haram.rothem.data.type.WeekStatus;
import org.haram.rothem.delegator.NoticeSpaceDelegator;
import org.haram.rothem.exception.bodycode.RothemErrorCode;
import org.haram.rothem.exception.exception.HaramEntityExistException;
import org.haram.rothem.exception.exception.HaramIllegalArgumentException;
import org.haram.rothem.mapper.user.*;
import org.haram.rothem.service.amenity.AmenityReplicaService;
import org.haram.rothem.service.calendar.CalendarReplicaService;
import org.haram.rothem.service.policy.PolicyReplicaService;
import org.haram.rothem.service.reservation.ReservationMasterService;
import org.haram.rothem.service.reservation.ReservationReplicaService;
import org.haram.rothem.service.room.RoomFileReplicaService;
import org.haram.rothem.service.room.RoomReplicaService;
import org.haram.rothem.service.time.TimeReplicaService;
import org.haram.rothem.service.time.TimeStatusMasterService;
import org.haram.rothem.service.time.TimeStatusReplicaService;
import org.haram.rothem.util.ReservationUtil;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Slf4j
public class UiDelegator {

    // ui
    private final UiMapper uiMapper;

    private final NoticeSpaceDelegator noticeSpaceDelegator;

    // room
    private final RoomMapper roomMapper;
    private final RoomFileMapper roomFileMapper;
    private final RoomReplicaService roomReplicaService;
    private final RoomFileReplicaService roomFileReplicaService;

    // amenity
    private final AmenityMapper amenityMapper;
    private final AmenityReplicaService amenityReplicaService;

    // reservation
    private final ReservationMapper reservationMapper;
    private final ReservationPolicyMapper reservationPolicyMapper;
    private final ReservationMasterService reservationMasterService;
    private final ReservationReplicaService reservationReplicaService;

    // policy
    private final PolicyMapper policyMapper;
    private final PolicyReplicaService policyReplicaService;

    // time
    private final TimeMapper timeMapper;
    private final TimeStatusMapper timeStatusMapper;
    private final TimeReplicaService timeReplicaService;
    private final TimeStatusMasterService timeStatusMasterService;
    private final TimeStatusReplicaService timeStatusReplicaService;

    // calendar
    private final CalendarMapper calendarMapper;
    private final CalendarReplicaService calendarReplicaService;


    public MainInfoResponse getMainInfo(String userId) {
        return MainInfoResponse.of(
                roomMapper.toResponses(roomReplicaService.findAll()),
                reservationReplicaService.existsByUserIdAndReservationStatus(userId, ReservationStatus.RESERVED) ? 1 : 0
        );
    }

    public RoomDetailResponse getRoomInfo(Long roomSeq) {
        // 스터디룸
        RoomResponse roomResponse = roomMapper.toResponse(roomReplicaService.findByIdAndStatusTrue(roomSeq));
        // 스터디룸 파일정보
        List<RoomFileResponse> roomFileResponses =
                roomFileMapper.toResponses(roomFileReplicaService.findAllByRoomSeq(roomSeq));
        // 스터디룸 편의기능
        List<AmenityResponse> amenityResponses =
                amenityMapper.toResponses(
                        roomReplicaService.findAllByRoomSeq(roomSeq)
                                .stream()
                                .map(roomAmenity -> amenityReplicaService.findById(roomAmenity.getAmenitySeq()))
                                .toList());
        return RoomDetailResponse.of(roomResponse, roomFileResponses, amenityResponses);
    }

    public RoomReservationTimeResponse getReservationTime(Long roomSeq) {
        // 스터디룸
        RoomResponse roomResponse = roomMapper.toResponse(roomReplicaService.findByIdAndStatusTrue(roomSeq));
        // 정책
        List<PolicyResponse> policyResponses = policyMapper.toResponses(policyReplicaService.findAll());

        // 예약가능날짜
        List<Calendar> availableCalendars = calendarReplicaService.findAllByWeekStatus(WeekStatus.AVAILABLE_WEEK);
        // 예약가능시간
        List<Time> availableTimes = timeReplicaService.findAllByAvailable();
        LocalDateTime now = LocalDateTime.now();
        // 사용 불가 시간
        Set<String> unavailableTimeInfo = new HashSet<>();
        List<TimeStatusUniqueKey> timeStatusUniqueKeys = new ArrayList<>();

        availableCalendars.forEach(calendar -> {
            availableTimes.forEach(time -> {
                if (isBeforeNow(calendar, time, now)) {
                    unavailableTimeInfo.add(calendar.getCalendarSeq() + "_" + time.getTimeSeq());
                    return;
                }

                timeStatusUniqueKeys.add(
                        TimeStatusUniqueKey.of(
                                roomResponse.getRoomSeq(),
                                calendar.getCalendarSeq(),
                                time.getTimeSeq(),
                                true));
            });
        });
        timeStatusReplicaService.findAllByTimeStatusKey(timeStatusUniqueKeys)
                .forEach(timeStatus -> unavailableTimeInfo.add(timeStatus.getCalendarSeq() + "_" + timeStatus.getTimeSeq()));

        List<AvailableCalendarResponse> calendarResponses = availableCalendars.stream()
                .map(calendar -> {
                    if (!calendar.getIsAvailable()) {
                        return uiMapper.toResponse(calendar);
                    }

                    List<AvailableTimeResponse> availableTimeResponses = availableTimes.stream()
                            .map(time -> uiMapper.toResponse(
                                    time,
                                    unavailableTimeInfo.contains(calendar.getCalendarSeq() + "_" + time.getTimeSeq())
                            ))
                            .collect(Collectors.toList());
                    return uiMapper.toResponse(calendar, availableTimeResponses);
                })
                .collect(Collectors.toList());

        return RoomReservationTimeResponse.of(roomResponse, policyResponses, calendarResponses);
    }


    //기존 v1과 호환을 위하여 고정값을 픽스하여 데이터를 전송
    public NoticeDetailResponse getNoticeInfo(Long noticeSeq) {

        NoticeDetailSpaceEntity noticeResponse = noticeSpaceDelegator.getNoticeDetail(noticeSeq);
        NoticeFileResponse file = NoticeFileResponse.builder()
                .seq(1L)
                .noticeSeq(1L)
                .sortNum(1)
                .filePath(noticeResponse.getThumbnailPath())
                .build();

        return NoticeDetailResponse.of(
                NoticeResponse.toNoticeResponse(noticeResponse),
                Collections.singletonList(file)
        );
    }

    public void reserve(ReservationRequest reservationRequest) {
        // 검증
        // 해당 회원이 예약중이거나 사용중인 예약 정보가 있는지 ?
        if (reservationReplicaService.existsByUserIdAndReservationStatus(reservationRequest.getUserId(), ReservationStatus.RESERVED)) {
            throw new HaramEntityExistException("2개의 예약을 동시에 진행할 수 없습니다.", RothemErrorCode.ALREADY_EXIST_RESERVATION_INFO);
        }

        // TODO 회원 자격에 따라 예약 가능 시간 달라짐(일반 사용자 ? 투플 ?) - 어떤 데이터로 판단할지 논의 필요
        if (reservationRequest.getTimeRequests().size() > 2) {
            throw new HaramIllegalArgumentException("1시간을 초과해서 예약할 수 없습니다.", RothemErrorCode.OVER_LIMIT_TIME);
        }

        // room 이 있는지 ?
        roomReplicaService.findByIdAndStatusTrue(reservationRequest.getRoomSeq());

        // calendar 이 있고, isAvailable true 인지 ?
        Calendar calendar = calendarReplicaService.findById(reservationRequest.getCalendarSeq());
        if (!calendar.getIsAvailable() || calendar.getWeekStatus() != WeekStatus.AVAILABLE_WEEK) {
            throw new HaramIllegalArgumentException("예약할 수 없는 날짜 정보입니다.", RothemErrorCode.UNAVAILABLE_CALENDAR);
        }

        // Time 이 있고, isAvailable 이 true 인지 ?
        List<Time> times = reservationRequest.getTimeRequests()
                .stream()
                .map(timeRequest -> {
                    Time time = timeReplicaService.findById(timeRequest.getTimeSeq());
                    if (!time.getIsAvailable()) {
                        throw new HaramIllegalArgumentException("예약할 수 없는 시간 정보입니다.", RothemErrorCode.UNAVAILABLE_TIME);
                    }
                    return time;
                })
                .sorted(Comparator.comparingLong(Time::getTimeSeq))
                .toList();
        // 각 time 의 time 오차가 30분인지
        for (int i = 0; i < times.size() - 1; i++) {
            if (times.get(i).getTimeSeq() + 1 != times.get(i + 1).getTimeSeq()) {
                throw new HaramIllegalArgumentException("요청 Time 의 정보가 올바르지 않습니다.", RothemErrorCode.NON_CONSECUTIVE_TIME);
            }
        }

        // Policy 가 있는지 ?
        Set<Long> agreedPolicySeqList = reservationRequest.getReservationPolicyRequests()
                .stream()
                .filter(reservationPolicyRequest -> reservationPolicyRequest.getPolicyAgreeYn() == 'Y')
                .map(ReservationPolicyRequest::getPolicySeq)
                .collect(Collectors.toSet());

        policyReplicaService.findAllIsRequiredTrue()
                .forEach(policy -> {
                    if (!agreedPolicySeqList.contains(policy.getPolicySeq())) {
                        throw new HaramIllegalArgumentException("필수 동의 약관이 동의 되지 않았습니다.", RothemErrorCode.NOT_CHECKED_ESSENTIAL_TERMS);
                    }
                });

        // reservation
        Reservation reservation = reservationMapper.toEntity(reservationRequest);
        reservation.setReservationStatus(ReservationStatus.RESERVED);
        reservation.setReservationCode(ReservationUtil.makeReservationCode());
        // time_status
        List<TimeStatus> timeStatuses = reservationRequest.getTimeRequests()
                .stream()
                .map(timeRequest -> timeStatusMapper.toEntity(
                        TimeStatusRequest.of(reservationRequest.getRoomSeq(),
                                reservationRequest.getCalendarSeq(),
                                timeRequest.getTimeSeq(),
                                ReservationType.RESERVED,
                                true)))
                .toList();
        // reservation_policy
        List<ReservationPolicy> reservationPolicies = reservationRequest.getReservationPolicyRequests()
                .stream()
                .map(reservationPolicyMapper::toEntity)
                .toList();
        reservationMasterService.save(reservation, timeStatuses, reservationPolicies);
    }

    public ReservationInfoResponse getReservationInfo(String userId) {
        // reservation 조회
        Reservation reservation = reservationReplicaService.findByUserIdAndReservationStatus(userId, ReservationStatus.RESERVED);

        // time_status 조회
        List<TimeStatus> timeStatuses = timeStatusReplicaService
                .findAllByReservationSeq(reservation.getReservationSeq());
        // room 조회
        RoomResponse roomResponse = roomMapper
                .toResponse(roomReplicaService.findById(timeStatuses.get(0).getRoomSeq()));
        // calendar 조회
        CalendarResponse calendarResponse = calendarMapper
                .toResponse(calendarReplicaService.findById(timeStatuses.get(0).getCalendarSeq()));
        // time 조회
        List<TimeResponse> timeResponses = timeStatuses.stream()
                .map(timeStatus -> timeMapper.toResponse(timeReplicaService.findById(timeStatus.getTimeSeq())))
                .toList();

        return uiMapper.toResponse(reservation, roomResponse, calendarResponse, timeResponses);
    }

    public void cancelReservation(ReservationDeleteRequest reservationDeleteRequest) {
        reservationMasterService.cancelReservation(reservationDeleteRequest);
    }

    private boolean isBeforeNow(Calendar calendar, Time time, LocalDateTime now) {
        LocalDateTime dateTime = toLocalDateTime(calendar, time);
        return dateTime.isBefore(now);
    }

    private LocalDateTime toLocalDateTime(Calendar calendar, Time time) {
        String dateStr = String.format("%02d-%02d-%02d",
                Integer.parseInt(calendar.getYear()), Integer.parseInt(calendar.getMonth()), Integer.parseInt(calendar.getDate()));
        String timeStr = convertTo24HourFormat(time);

        return LocalDateTime.parse(dateStr + " " + timeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    private String convertTo24HourFormat(Time time) {
        int hour = Integer.parseInt(time.getHour());
        if ("pm".equalsIgnoreCase(time.getMeridiem()) && hour != 12) {
            hour += 12;
        } else if ("am".equalsIgnoreCase(time.getMeridiem()) && hour == 12) {
            hour = 0;
        }
        return String.format("%02d:%02d", hour, Integer.parseInt(time.getMinute()));
    }

}
