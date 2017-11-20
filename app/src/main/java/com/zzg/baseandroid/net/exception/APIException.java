package com.zzg.baseandroid.net.exception;

/**
 * 自定义异常，当接口返回的status != 1时，需要跑出此异常
 * eg：登陆时验证码错误；参数为传递等
 */
public class APIException extends Exception {
    public ResultStatusEnum status;
    public String message;

    public APIException(ResultStatusEnum status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public boolean doAction(){
        if(status == ResultStatusEnum.CLOUD_TOKEN_TIME_OUT ||
                status == ResultStatusEnum.CLOUD_TOKEN_OFFLINE ||
                status == ResultStatusEnum.NO_LOGIN ){
            return true;
        }
        return false;
    }
}