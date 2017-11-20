package com.zzg.baseandroid.ui.login.contract;

import com.zzg.baseandroid.ui.login.presenter.LoginPresenterImpl;

/**
 * @author zhongzhigang
 * @Description:
 * @date 2017/11/2
 */
public interface LoginContract {
    interface Model {
       void login(String phone, String password , LoginPresenterImpl loginPresenter);
    }

    interface View {
        void shouToast(String msg);

        void onSuccess();
    }

    interface Presenter {
        void onSuccess();//登陆成功

        void onfail(String msg);//登陆失败

        void login(String phone, String password );
    }
}
