package com.example.mockserver.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultMsg<T> {
    public static final String SUCCESS_NO = "200";
    public static final String SUCCESS_SUCCESS = "success";

    private String no;
    private String msg;
    private T data;

    public ResultMsg(String no, String msg) {
        this.no = no;
        this.msg = msg;
    }

    public static <T> ResultMsg<T> success(T data){
        return new ResultMsg(SUCCESS_NO,SUCCESS_SUCCESS,data);
    }

    public static <T> ResultMsg<T> error(String no,String msg){
        return new ResultMsg(no,msg);
    }
}
