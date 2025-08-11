package org.haram.rothem.exception.bodycode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BoardErrorCode implements BodyCode {

    INVALID_VALUE("BD01", "요청 값이 올바르지 않습니다.", 400),
    ALREADY_EXIST_ENTITY("BD02", "이미 존재하는 값입니다.", 400),
    NOT_FOUND_ENTITY("BD03", "존재하지 않는 값입니다.", 400),
    NO_PERMISSION_TO_WRITE("BD04", "글을 쓸 권한이 없습니다.", 400),
    CANNOT_WRITE_COMMENT("BD05", "댓글을 쓸 수 없습니다.", 400),
    NO_PERMISSION_TO_MODIFY("BD06", "글을 수정할 권한이 없습니다.", 400),
    NOT_ALLOWED_ANONYMOUS_REGISTRATION("BD07", "익명 등록이 불가능합니다.", 400),
    NO_PERMISSION_TO_MODIFY_COMMENT("BD08", "댓글을 수정할 권한이 없습니다.", 400),
    NO_PERMISSION_TO_DELETE_BOARD("BD09", "글을 삭제할 권한이 없습니다.", 400),
    NO_PERMISSION_TO_DELETE_COMMENT("BD10", "댓글을 삭제할 권한이 없습니다.", 400),
    INVALID_VALUE_IMAGE("BD11", "요청 이미지가 올바르지 않습니다.", 400),
    NO_PERMISSION_TO_MODIFY_CATEGORY("BD12", "카테고리를 수정할 권한이 없습니다.", 400),
    NO_PERMISSION_TO_DELETE_CATEGORY("BD13", "카테고리를 수정할 권한이 없습니다.", 400),
    ILLEGAL_PAGE("BD14", "Page 값은 1보다 작을 수 없습니다.", 400),
    NOT_FOUND_BOARD_CATEGORY("BD15", "게시글 카테고리가 존재하지 않습니다.", 400),
    NOT_FOUND_BOARD("BD16", "게시글이 존재하지 않습니다.", 400),
    ILLEGAL_TITLE("BD17", "제목 값이 올바르지 않습니다.", 400),
    ILLEGAL_IS_ANONYMOUS("BD18", "익명 여부 값이 올바르지 않습니다.", 400),
    ALREADY_EXIST_BOARD("BD19", "Board 가 이미 존재합니다.", 400),
    ILLEGAL_CONTENTS("BD20", "내용 값이 올바르지 않습니다.", 400),
    ALREADY_EXIST_COMMENT("BD21", "Comment 가 이미 존재합니다.", 400),
    ALREADY_EXIST_BOARD_CATEGORY("BD22", "Board Category 가 이미 존재합니다.", 400),
    NOT_FOUND_COMMENT("BD23", "Comment 가 존재하지 않습니다.", 400),
    ALREADY_EXIST_REPORT("BD24", "Report 가 이미 존재합니다.", 400),
    ALREADY_EXIST_BLOCK_LIST("BD25", "BlockList 가 이미 존재합니다.", 400),
    ;

    private final String code;
    private final String message;
    private final Integer status;

}
