package com.zzg.baseandroid.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.kennyc.view.MultiStateView;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zzg.baseandroid.R;
import com.zzg.baseandroid.util.ToastUtils;

import butterknife.ButterKnife;

/**
 * @author zhongzhigang
 *         created at 2017/11/1
 * @file_name BaseActivity.java
 * @description: 基础activity
 */
public abstract class BaseActivity extends RxAppCompatActivity {

    protected MultiStateView mMultiStateView;
    protected Toolbar toolbar;
    private ClickInterface clickInterface;

    public interface ClickInterface {
        void leftClick();

        void reLoadData();
    }

    public void setClickInterface(ClickInterface clickInterface) {
        this.clickInterface = clickInterface;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (null != extras) {
            getBundleExtras(extras);
        }
        initContentView(savedInstanceState);
        initView();
        initLogic();
    }


    protected abstract void initContentView(Bundle bundle);

    /**
     * Bundle  传递数据
     *
     * @param extras
     */
    protected abstract void getBundleExtras(Bundle extras);

    protected abstract void initView();

    protected abstract void initLogic();

    public void initMultiView(int layoutIds) {
        initMultiView(layoutIds, false);
    }

    public void initMultiView(int layoutIds, boolean hasMultiView) {
        View rootView = getLayoutInflater()
                .inflate(R.layout.activity_base, null);
        mMultiStateView = rootView.findViewById(R.id.detail_multistate_view);
        View childView = null;
        if (layoutIds != 0) {
            childView = getLayoutInflater().inflate(layoutIds, null);
        }
        ButterKnife.bind(this, childView);
        if (childView != null) {
            childView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT));
            mMultiStateView.addView(childView, 0);
        }
        setContentView(rootView);
        if (mMultiStateView != null) {
            mMultiStateView.getView(MultiStateView.VIEW_STATE_ERROR).setOnClickListener(v -> errorRetryClick());
        }
        toolbar = rootView.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.arrow_back);
        toolbar.setTitleTextColor(getResources().getColor(R.color.color_FFFFFF));
        toolbar.setNavigationOnClickListener(v -> {
            if (clickInterface == null) {
                finish();
            } else {
                clickInterface.leftClick();
            }
        });
    }

    protected void errorRetryClick() {
        if (clickInterface != null) {
            clickInterface.reLoadData();
        }
    }

    public void showMultiViewStateContent() {
        if (mMultiStateView != null) {
            mMultiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
        }
    }

    public void showMultiViewStateLoading() {
        if (mMultiStateView != null) {
            mMultiStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
        }
    }

    public void showMultiViewStateError() {
        if (mMultiStateView != null) {
            mMultiStateView.setViewState(MultiStateView.VIEW_STATE_ERROR);
        }
    }

    public void showMultiViewStateEmpty() {
        if (mMultiStateView != null) {
            mMultiStateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
        }
    }

    //    //Toast显示
        protected void showShortToast(String string) {
            ToastUtils.showShortToast(this, string);
        }

        protected void showShortToast(int stringId) {
            ToastUtils.showShortToast(this, stringId);
        }

        protected void showLongToast(String string) {
            ToastUtils.showShortToast(this, string);
        }

        protected void showLongToast(int stringId) {
            ToastUtils.showShortToast(this, stringId);
        }

    /**
     * 界面跳转
     *
     * @param cls 目标Activity
     */
    protected void readyGo(Class<?> cls) {
        readyGo(cls, null);
    }

    /**
     * 跳转界面，传参
     *
     * @param cls    目标Activity
     * @param bundle 数据
     */
    protected void readyGo(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        if (null != bundle)
            intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 跳转界面并关闭当前界面
     *
     * @param cls 目标Activity
     */
    protected void readyGoThenKill(Class<?> cls) {
        readyGoThenKill(cls, null);
    }

    /**
     * @param cls    目标Activity
     * @param bundle 数据
     */
    protected void readyGoThenKill(Class<?> cls, Bundle bundle) {
        readyGo(cls, bundle);
        finish();
    }

    /**
     * startActivityForResult
     *
     * @param cls         目标Activity
     * @param requestCode 发送判断值
     */
    protected void readyGoForResult(Class<?> cls, int requestCode) {
        Intent intent = new Intent(this, cls);
        startActivityForResult(intent, requestCode);
    }

    /**
     * startActivityForResult with bundle
     *
     * @param cls         目标Activity
     * @param requestCode 发送判断值
     * @param bundle      数据
     */
    protected void readyGoForResult(Class<?> cls, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }


}
