package com.johnyehyo.imgencry.vo;

import com.johnyehyo.imgencry.enums.ResponseEnum;

/**
 * @description:
 * @author: JohnYehyo
 * @create: 2022-06-30 22:49:16
 */
public class ResponseVo {

    public ResponseVo() {
    }

    public ResponseVo(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseVo(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private int code;

    private String msg;

    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResponseVo(Integer code, String msg) {
        this(code, msg, null);
    }


    public static ResponseVo response(ResponseEnum responseEnum, Object obj) {
        return new ResponseVo(responseEnum.getCode(), responseEnum.getValue(), obj);
    }

    public static ResponseVo success() {
        return new ResponseVo(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getValue());
    }

    public static ResponseVo success(String msg) {
        return new ResponseVo(ResponseEnum.SUCCESS.getCode(), msg);
    }

    public static ResponseVo error(Integer code, String msg) {
        return new ResponseVo(code, msg);
    }

    public static ResponseVo error(String msg) {
        return new ResponseVo(ResponseEnum.FAIL.getCode(), msg);
    }

    public static ResponseVo error() {
        return new ResponseVo(ResponseEnum.EXCEPTION.getCode(), ResponseEnum.EXCEPTION.getValue());
    }

    @Override
    public String toString() {
        return "ResponseVo{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
