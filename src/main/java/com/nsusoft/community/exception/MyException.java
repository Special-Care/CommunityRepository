package com.nsusoft.community.exception;

public class MyException extends RuntimeException{
    private String message;

    public MyException(MyHttpStatusCode myHttpStatusCode) {
        this.message = myHttpStatusCode.getMessage();
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
