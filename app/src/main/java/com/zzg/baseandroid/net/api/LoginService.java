package com.zzg.baseandroid.net.api;

import com.zzg.baseandroid.net.HttpResponse;
import com.zzg.baseandroid.net.request.LoginRequest;
import com.zzg.baseandroid.net.response.LoginResponse;
import com.zzg.baseandroid.ui.base.BaseActivity;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;


/**
 * @author zhongzhigang
 * @Title: ${FILE_NAME}
 * @Description:
 * @package_name: sanocare.minute.clinic.net.api
 * @date 2017/6/23
 */
public class LoginService extends BaseApiService {

    private interface LoginServiceAPi {

        @POST("/operation/user/login")
        public Observable<HttpResponse<LoginResponse>> login(@Body LoginRequest loginRequest);

        @GET("/operation/user/logout")
        public Observable<HttpResponse<Boolean>> logout();

    }

    private static LoginService INSTANCE = null;

    private LoginService(BaseActivity activity) {
                super(activity);
    }

    public static LoginService getLoginService(BaseActivity activity) {
        if(INSTANCE == null){
            INSTANCE = new LoginService(activity);
        }
        return INSTANCE;
    }

    /**
     * param count
     * loginRequestram password
     *
     * @return
     */
    public Observable<LoginResponse> login(LoginRequest loginRequest) {
        return createRetrofit(LoginService.LoginServiceAPi.class)
                .login(loginRequest)
                .compose(this.<LoginResponse>applySchedulers());
    }

    /**
     * param count
     loginRequestram password
     * @return
     */
    public Observable<Boolean> logout() {
        return createRetrofit(LoginService.LoginServiceAPi.class)
                .logout()
                .compose(this.<Boolean>applySchedulers());
    }
}
