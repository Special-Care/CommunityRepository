package com.nsusoft.community.exception;

public enum MyHttpStatus implements MyHttpStatusCode {
    QUESTION_NOT_FOUND("该问题不存在，请换一个试试~");
    
    private String message;

    MyHttpStatus(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
