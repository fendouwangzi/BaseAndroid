package com.zzg.baseandroid.ui.login;

import android.os.Bundle;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.trello.rxlifecycle.ActivityEvent;
import com.zzg.baseandroid.R;
import com.zzg.baseandroid.event.BusEvents;
import com.zzg.baseandroid.event.RxBus;
import com.zzg.baseandroid.ui.base.BaseActivity;
import com.zzg.baseandroid.ui.login.contract.LoginContract;
import com.zzg.baseandroid.ui.login.presenter.LoginPresenterImpl;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;


/**
 * @author zhongzhigang
 *         created at 2017/11/1
 * @file_name LoginActivity.java
 * @description: demo
 */
public class LoginActivity extends BaseActivity implements BaseActivity.ClickInterface, LoginContract.View {

    private static final String TAG = "LoginActivity";
    @BindView(R.id.test_name_tv)
    TextView testNameTv;

    private LoginContract.Presenter presenter;

    @Override
    protected void initContentView(Bundle bundle) {
        initMultiView(R.layout.activity_login);
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void initView() {
        testNameTv.setText("hello baseAndroid");
        loginClick();
    }

    private void loginClick() {
        RxView.clicks( testNameTv )
                .throttleFirst( 2 , TimeUnit.SECONDS )   //两秒钟之内只取一个点击事件，防抖操作
                .subscribe(aVoid -> presenter.login("18974931832", "123456")) ;
    }

    @Override
    protected void initLogic() {
        setClickInterface(this);
        presenter = new LoginPresenterImpl(this);
        loading();
        bindBus();
    }

    private void bindBus() {
        RxBus.bind(this)
                .setEventCode(BusEvents.TAP)
                .setEndLifeEvent(ActivityEvent.DESTROY) //不设置默认与fragment生命周期同步
                .onNext(events -> {
                    String content = events.getContent();
                    testNameTv.setText(content);
                })
                .onError(throwable -> testNameTv.setText(throwable.toString())) // 异常处理，默认捕获异常，不做处理，程序不会crash。
                .create();
    }

    private void loading() {
        Observable.interval(5, 5, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .compose(this.<Long>bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                showMultiViewStateError();
            }

            @Override
            public void onNext(Long aLong) {
                showMultiViewStateContent();
            }
        });
    }


    @Override
    public void leftClick() {

//        finish();
        RxBus.getInstance().send(BusEvents.TAP, "想要结束activity？");
    }

    @Override
    public void reLoadData() {
        showMultiViewStateContent();

    }


    @Override
    public void shouToast(String msg) {
        showShortToast(msg);
    }

    @Override
    public void onSuccess() {
        showMultiViewStateError();
    }
}
