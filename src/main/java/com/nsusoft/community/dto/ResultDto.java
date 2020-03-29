package com.nsusoft.community.dto;

import com.nsusoft.community.exception.MyHttpStatus;
import lombok.Data;

@Data
public class ResultDto {
    private int code;
    private String message;

    public static ResultDto result(int code, String message) {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(code);
        resultDto.setMessage(message);
        return resultDto;
    }

    public static ResultDto result(MyHttpStatus noLogin) {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(noLogin.getCode());
        resultDto.setMessage(noLogin.getMessage());
        return resultDto;
    }
}
