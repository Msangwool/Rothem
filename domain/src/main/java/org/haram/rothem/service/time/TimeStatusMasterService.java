package org.haram.rothem.service.time;

import org.haram.rothem.data.type.ReservationType;
import org.haram.rothem.exception.bodycode.RothemErrorCode;
import org.haram.rothem.exception.exception.HaramEntityExistException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.haram.rothem.data.dto.admin.request.TimeStatusRequest;
import org.haram.rothem.data.entity.TimeStatus;
import org.haram.rothem.data.entity.TimeStatusUniqueKey;
import org.haram.rothem.mapper.admin.AdminTimeStatusMapper;
import org.haram.rothem.repository.dao.TimeStatusDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TimeStatusMasterService {

    private final TimeStatusDao timeStatusDao;

    private final AdminTimeStatusMapper timeStatusMapper;

    public void save(TimeStatus timeStatus) {
        timeStatus.setReservationSeq(-1L);
        timeStatusDao.save(timeStatus)
                .orElseThrow(() -> new HaramEntityExistException("이미 존재하는 TimeStatus 입니다.", RothemErrorCode.ALREADY_EXIST_TIME_STATUS));
    }

    public void saveAll(List<TimeStatus> timeStatusList) {
        for (TimeStatus timeStatus : timeStatusList) {
            timeStatus.setReservationSeq(-1L);
        }
        timeStatusDao.saveAll(timeStatusList);
    }

    public void setIsAvailableByReservationSeq(Long reservationSeq, boolean isAvailable) {
        List<TimeStatus> timeStatuses = timeStatusDao.findAllByReservationSeq(reservationSeq);
        for (TimeStatus timeStatus : timeStatuses) {
            timeStatus.setIsAvailable(isAvailable);
        }
    }

    public void expiredTimeStatus(List<Long> expiredReservationSeqs) {
        List<TimeStatus> timeStatuses = timeStatusDao.findAllByReservationSeqList(expiredReservationSeqs);
        for (TimeStatus timeStatus : timeStatuses) {
            timeStatus.setIsAvailable(false);
        }
    }

    public void processUnavailableTimeStatus(Map<Long, TimeStatusUniqueKey> beCreatedMap, List<TimeStatusUniqueKey> beModifiedList) {
        // isAvailable == true 인 데이터가 이미 존재하는 경우를 제외하고는 전부 생성
        createUnavailableTimeStatus(beCreatedMap);
        // isAvailable == true 인 데이터가 존재하는 경우에만 false 로 변경
        modifyUnavailableTimeStatus(beModifiedList);
    }

    private void createUnavailableTimeStatus(Map<Long, TimeStatusUniqueKey> timeStatusUniqueKeyMap) {
        // 생성될 데이터 셋
        List<TimeStatusUniqueKey> timeStatusUniqueKeys = timeStatusUniqueKeyMap.values().stream().toList();

        // 생성될 데이터 셋 중 이미 존재하는 데이터 목록
        List<Long> existingKeys = timeStatusDao.findAllByTimeStatusKey(timeStatusUniqueKeys).stream()
                .map(TimeStatus::getTimeSeq)
                .toList();

        // 이미 존재하는 데이터 제외
        existingKeys.forEach(timeStatusUniqueKeyMap::remove);

        // entity 생성
        List<TimeStatus> newTimeStatusEntities = timeStatusUniqueKeyMap.values()
                .stream()
                .map(timeStatusUniqueKey ->
                        timeStatusMapper.toEntity(TimeStatusRequest.of(
                                timeStatusUniqueKey.getRoomSeq(), timeStatusUniqueKey.getCalendarSeq(),
                                timeStatusUniqueKey.getTimeSeq(), ReservationType.UNAVAILABLE, true)))
                .toList();
        saveAll(newTimeStatusEntities);
    }

    private void modifyUnavailableTimeStatus(List<TimeStatusUniqueKey> timeStatusUniqueKeyList) {
        if (timeStatusUniqueKeyList.isEmpty()) {
            return;
        }
        timeStatusDao.modifyUnavailableTimeStatus(timeStatusUniqueKeyList);
    }

}
