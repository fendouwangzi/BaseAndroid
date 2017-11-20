package com.zzg.baseandroid.ui.login.model;

import com.trello.rxlifecycle.ActivityEvent;
import com.zzg.baseandroid.net.api.LoginService;
import com.zzg.baseandroid.net.base.BaseSubscriber;
import com.zzg.baseandroid.net.exception.APIException;
import com.zzg.baseandroid.net.request.LoginRequest;
import com.zzg.baseandroid.net.response.LoginResponse;
import com.zzg.baseandroid.ui.login.contract.LoginContract;
import com.zzg.baseandroid.ui.login.presenter.LoginPresenterImpl;

/**
 * @author zhongzhigang
 * @Description:
 * @date 2017/11/2
 */
public class LoginModelImpl implements LoginContract.Model {
    private static final String TAG = "LoginModelImpl";

    @Override
    public void login(String phone, String password, LoginPresenterImpl loginPresenter) {
        LoginService.getLoginService(loginPresenter.getContext()).login(new LoginRequest(phone, password))
                .compose(loginPresenter.getContext().bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseSubscriber<LoginResponse>(loginPresenter.getContext()) {

                    @Override
                    protected void onError(APIException ex) {
                        loginPresenter.onfail(ex.getMessage());
                    }

                    @Override
                    public void onNext(LoginResponse loginResponse) {
                        loginPresenter.onSuccess();
                    }
                });

    }

}
