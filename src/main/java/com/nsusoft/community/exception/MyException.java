package com.nsusoft.community.exception;

public class MyException extends RuntimeException {
    private String message;
    private Integer code;

    public MyException(MyHttpStatusCode myHttpStatusCode) {
        this.message = myHttpStatusCode.getMessage();
        this.code = myHttpStatusCode.getCode();
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
