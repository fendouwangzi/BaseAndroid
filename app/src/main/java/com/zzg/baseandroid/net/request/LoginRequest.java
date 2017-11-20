package com.zzg.baseandroid.net.request;

import java.io.Serializable;

/**
 * @author zhongzhigang
 * @Title: ${FILE_NAME}
 * @Description:
 * @package_name: sanocare.minute.clinic.net.request
 * @date 2017/6/23
 */
public class LoginRequest implements Serializable{
    private String count;
    private String password;

    public LoginRequest(String count, String password) {
        this.count = count;
        this.password = password;
    }
}
