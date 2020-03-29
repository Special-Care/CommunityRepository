package com.nsusoft.community.exception;

public enum MyHttpStatus implements MyHttpStatusCode {
    QUESTION_NOT_FOUND(000, "该问题不存在，请换一个试试~"),
    TARGET_PARENT_NOT_FOUND(399, "未获取到问题或评论,暂不能回复"),
    NO_LOGIN(502, "未登录"),
    COMMENT_SUCCESS(520, "评论成功"),
    SYSTEM_ERROR(4004, "服务器异常错误"),
    TYPE_PARENT_WRONG(388, "评论类型不匹配"),
    COMMENT_PARENT_WRONG(377, "回复的评论不存在");

    private Integer code;
    private String message;

    MyHttpStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
