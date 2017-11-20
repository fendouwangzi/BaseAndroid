package com.zzg.baseandroid.net.base;

import android.os.Handler;
import android.util.Log;

import com.zzg.baseandroid.net.exception.APIException;
import com.zzg.baseandroid.net.exception.ResultStatusEnum;
import com.zzg.baseandroid.ui.base.BaseActivity;

import rx.Subscriber;


/**
 * @author zhongzhigang
 * @Title: ${FILE_NAME}
 * @Description:
 * @package_name: sanocare.minute.clinic.net.base
 * @date 2017/7/18
 */
public abstract class BaseSubscriber<T> extends Subscriber<T> {

    private BaseActivity context;

    public BaseSubscriber(BaseActivity context) {
        this.context = context;
    }

    @Override
    public void onStart() {
        super.onStart();
//        if (!CommonUtil.checkNetWork(context)) {
//            context.showMsg("网络不可用，请先确保网络畅通");
//            onCompleted();
//            return;
//        }
//        // 显示进度条
//        context.showProgressDialog();
    }

    @Override
    public void onCompleted() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //关闭等待进度条
//                context.dismissProgressDialog();
            }
        }, 500);
    }

    @Override
    public void onError(Throwable e) {
        //        e.printStackTrace();
        if (e instanceof APIException) {
            onError((APIException) e);
        } else {
            Log.e("zgzg","eee----------="+e.toString());
//            context.showMsg("网络异常");
            onError(new APIException(ResultStatusEnum.NETWORK_CANNOT_LINK,ResultStatusEnum.NETWORK_CANNOT_LINK.description));
        }
        onCompleted();
    }

    /**
     * 错误回调
     */
    protected abstract void onError(APIException ex);

}
