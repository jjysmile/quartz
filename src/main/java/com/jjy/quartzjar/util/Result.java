package com.jjy.quartzjar.util;/*
@author JiaYun
@creat 2021-06-17 19:59
*/


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 3618584226871196529L;

    //状态码
    private int code;

    //数据
    private T data;

    public static <T> Result<T> response(int code, T data){
        Result r = new Result();
        r.setCode(code);
        r.setData(data);
        return r;
    }


}
