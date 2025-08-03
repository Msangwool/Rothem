package org.haram.rothem.service.time;

import com.space.data.domain.rothem.admin.request.TimeAvailableRequest;
import com.space.domain.rothem.entity.Time;
import com.space.domain.rothem.repository.dao.TimeDao;
import com.space.exception.bodycode.RothemErrorCode;
import com.space.exception.space.SpaceEntityNotFoundException;
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
                .orElseThrow(() -> new SpaceEntityNotFoundException("Time 이 존재하지 않습니다.", RothemErrorCode.NOT_FOUND_TIME));

        currentTime.setIsAvailable(timeStatusRequest.getIsAvailable());
    }

}
