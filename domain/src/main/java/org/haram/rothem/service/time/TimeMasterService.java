package org.haram.rothem.service.time;

import org.haram.rothem.data.dto.admin.request.TimeAvailableRequest;
import org.haram.rothem.data.entity.Time;
import org.haram.rothem.repository.dao.TimeDao;
import org.haram.rothem.exception.bodycode.RothemErrorCode;
import org.haram.rothem.exception.exception.HaramEntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TimeMasterService {

    private final TimeDao timeDao;

    public void modifyAllAvailable(List<TimeAvailableRequest> timeAvailableRequestList) {
        Map<Long, Boolean> availableInfo = timeAvailableRequestList
                .stream()
                .collect(Collectors.toMap(TimeAvailableRequest::getTimeSeq, TimeAvailableRequest::getIsAvailable,
                        (a, b) -> b));

        List<Time> currentTimeList =
                timeDao.findAllByTimeSeqList(
                        timeAvailableRequestList.stream().map(TimeAvailableRequest::getTimeSeq).toList());

        for (Time time : currentTimeList) {
            time.setIsAvailable(availableInfo.get(time.getTimeSeq()));
        }
    }

    public void updateTimeStatus(TimeAvailableRequest timeStatusRequest) {
        Time currentTime = timeDao.findById(timeStatusRequest.getTimeSeq())
                .orElseThrow(() -> new HaramEntityNotFoundException("Time 이 존재하지 않습니다.", RothemErrorCode.NOT_FOUND_TIME));

        currentTime.setIsAvailable(timeStatusRequest.getIsAvailable());
    }

}
