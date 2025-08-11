package org.haram.rothem.service.room;

import org.haram.rothem.data.dto.admin.request.RoomSortRequest;
import org.haram.rothem.data.entity.Room;
import org.haram.rothem.data.entity.RoomAmenity;
import org.haram.rothem.repository.dao.RoomAmenityDao;
import org.haram.rothem.repository.dao.RoomDao;
import org.haram.rothem.exception.bodycode.BoardErrorCode;
import org.haram.rothem.exception.bodycode.RothemErrorCode;
import org.haram.rothem.exception.exception.HaramEntityExistException;
import org.haram.rothem.exception.exception.HaramEntityNotFoundException;
import org.haram.rothem.exception.exception.HaramException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RoomMasterService {

    private final RoomDao roomDao;
    private final RoomAmenityDao roomAmenityDao;

    public Room save(Room room) {
        return roomDao.save(room)
                .orElseThrow(() -> new HaramEntityExistException("이미 Room 이 존재합니다.", RothemErrorCode.ALREADY_EXIST_ROOM));
    }

    public Room save(Room room, List<RoomAmenity> roomAmenities) {
        Room savedRoom = roomDao.save(room)
                .orElseThrow(() -> new HaramEntityExistException("이미 Room 이 존재합니다.", RothemErrorCode.ALREADY_EXIST_ROOM));

        if (roomAmenities != null) {
            for (RoomAmenity roomAmenity : roomAmenities) {
                roomAmenity.setRoomSeq(savedRoom.getRoomSeq());
            }
            roomAmenityDao.saveAll(roomAmenities);
        }

        return savedRoom;
    }

    public Room modify(Room room, List<RoomAmenity> roomAmenities) {
        Room currentRoom = roomDao.findByRoomSeqAndStatusTrue(room.getRoomSeq())
                .orElseThrow(() -> new HaramEntityNotFoundException("Room 이 존재하지 않습니다.", RothemErrorCode.NOT_FOUND_ROOM));

        Optional.ofNullable(room.getThumbnailPath()).ifPresent(currentRoom::setThumbnailPath);
        Optional.ofNullable(room.getRoomName()).ifPresent(currentRoom::setRoomName);
        Optional.ofNullable(room.getRoomExplanation()).ifPresent(currentRoom::setRoomExplanation);
        Optional.ofNullable(room.getLocation()).ifPresent(currentRoom::setLocation);
        Optional.of(room.getPeopleCount()).ifPresent(currentRoom::setPeopleCount);
        Optional.of(room.getSortNum()).ifPresent(currentRoom::setSortNum);

        if (roomAmenities == null || roomAmenities.isEmpty()) {
            return currentRoom;
        }

        Set<Long> requestAmenitySeqList = roomAmenities
                .stream()
                .map(RoomAmenity::getAmenitySeq)
                .collect(Collectors.toSet());

        List<RoomAmenity> foundedRoomAmenities = roomAmenityDao.findAllByRoomSeq(room.getRoomSeq());
        Set<Long> savedAmenitySeqList = foundedRoomAmenities
                .stream()
                .map(RoomAmenity::getAmenitySeq)
                .collect(Collectors.toSet());

        // 저장된 Amenity 가 요청값에 없다면 삭제
        roomAmenityDao.deleteAll(foundedRoomAmenities
                .stream()
                .filter(roomAmenity -> !requestAmenitySeqList.contains(roomAmenity.getAmenitySeq()))
                .toList());
        // 요청된 Amenity 가 저장된 값에 없다면 생성
        roomAmenityDao.saveAll(roomAmenities
                .stream()
                .filter(roomAmenity -> !savedAmenitySeqList.contains(roomAmenity.getAmenitySeq()))
                .peek(roomAmenity -> roomAmenity.setRoomSeq(currentRoom.getRoomSeq()))
                .toList());
        return currentRoom;
    }

    public void delete(List<Long> roomSeqs) throws HaramException {
        try {
            List<Room> roomList = roomDao.findAllById(roomSeqs);
            for (Room room : roomList) {
                room.setStatus(false);
            }
            roomAmenityDao.delete(roomSeqs);
        } catch (Exception e) {
            log.error("[RoomMasterService] delete -> room 리스트 삭제 실패, roomSeq -> {}, class={}, message={}",
                    roomSeqs.toString(), e.getClass(), e.getMessage());
            throw new HaramException(BoardErrorCode.INVALID_VALUE);
        }
    }

    public void sort(List<RoomSortRequest> roomSortRequests) {
        Map<Long, Integer> sortInfo = roomSortRequests.stream()
                .collect(Collectors.toMap(RoomSortRequest::getRoomSeq, RoomSortRequest::getSortNum));

        List<Room> rooms = roomDao.findAllById(roomSortRequests.stream()
                .map(RoomSortRequest::getRoomSeq).toList());

        int maxSortNum = roomDao.findFirstOrderBySortNum()
                .map(Room::getSortNum)
                .orElse(0);
        for (Room room : rooms) {
            room.setSortNum(sortInfo.getOrDefault(room.getRoomSeq(), maxSortNum + 1));
        }
    }

}
