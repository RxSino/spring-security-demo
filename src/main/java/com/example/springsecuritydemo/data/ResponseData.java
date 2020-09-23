package com.example.springsecuritydemo.data;

public class ResponseData {

    public String code;
    public String message;
    public String token;

    public static ResponseData unknownError() {
        ResponseData data = new ResponseData();
        data.code = "-1";
        data.message = "未知错误";
        return data;
    }

    public static ResponseData signatureError() {
        ResponseData data = new ResponseData();
        data.code = "-1";
        data.message = "接口校验失败";
        return data;
    }

    public static ResponseData tokenError() {
        ResponseData data = new ResponseData();
        data.code = "-1";
        data.message = "Token错误";
        return data;
    }

    public static ResponseData permissionError() {
        ResponseData data = new ResponseData();
        data.code = "-1";
        data.message = "权限错误";
        return data;
    }

    public static ResponseData loginSuccess(String token) {
        ResponseData data = new ResponseData();
        data.code = "0";
        data.message = "登录成功";
        data.token = token;
        return data;
    }

    public static ResponseData loginFailed() {
        ResponseData data = new ResponseData();
        data.code = "-2";
        data.message = "用户名或密码错误";
        return data;
    }

}
