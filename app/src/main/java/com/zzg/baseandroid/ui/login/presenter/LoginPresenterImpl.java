package com.zzg.baseandroid.ui.login.presenter;

import com.zzg.baseandroid.ui.base.BaseActivity;
import com.zzg.baseandroid.ui.login.contract.LoginContract;
import com.zzg.baseandroid.ui.login.model.LoginModelImpl;

/**
 * @author zhongzhigang
 * @Description:
 * @date 2017/11/2
 */
public class LoginPresenterImpl implements LoginContract.Presenter {
    private LoginContract.View view ;
    private LoginContract.Model model;

    public LoginPresenterImpl(LoginContract.View view) {
        this.view = view;
        model = new LoginModelImpl();
    }

    public BaseActivity getContext() {
        return (BaseActivity)view;
    }

    @Override
    public void onSuccess() {
        view.onSuccess();
    }

    @Override
    public void onfail(String msg) {
        view.shouToast(msg);
    }

    @Override
    public void login(String phone, String password) {
        model.login(phone,password,this);
    }
}
