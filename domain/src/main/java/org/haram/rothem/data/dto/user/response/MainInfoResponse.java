package org.haram.rothem.data.dto.user.response;

import com.space.data.domain.notice.space.NoticeResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
public class MainInfoResponse {

    private List<NoticeResponse> noticeResponses;

    private List<RoomResponse> roomResponses;

    private int isReserved;

}
