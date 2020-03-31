package com.nsusoft.community.dto;

import com.nsusoft.community.exception.MyHttpStatus;
import lombok.Data;

@Data
public class ResultDto<E> {
    private int code;
    private String message;
    private E data;

    public static ResultDto result(MyHttpStatus noLogin) {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(noLogin.getCode());
        resultDto.setMessage(noLogin.getMessage());
        return resultDto;
    }

    public static <E> ResultDto result(MyHttpStatus noLogin, E e) {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(noLogin.getCode());
        resultDto.setMessage(noLogin.getMessage());
        resultDto.setData(e);
        return resultDto;
    }
}
