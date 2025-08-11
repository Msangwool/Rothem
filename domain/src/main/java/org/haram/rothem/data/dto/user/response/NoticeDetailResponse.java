package org.haram.rothem.data.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.haram.rothem.data.dto.notice.NoticeResponse;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
public class NoticeDetailResponse {

    private NoticeResponse noticeResponse;

    private List<NoticeFileResponse> noticeFileResponses;

}
