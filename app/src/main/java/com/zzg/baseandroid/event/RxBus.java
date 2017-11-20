package com.zzg.baseandroid.event;

import com.trello.rxlifecycle.ActivityEvent;
import com.trello.rxlifecycle.ActivityLifecycleProvider;
import com.trello.rxlifecycle.FragmentEvent;
import com.trello.rxlifecycle.FragmentLifecycleProvider;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * @file_name RxBus.java
 * @description: 使用RxJava代替EventBus的方案
 * @author zhongzhigang
 * created at 2017/11/3
 */

/** 使用实例 , 参考地址：https://github.com/CloudWoo/RxBusDemo
 * https://juejin.im/entry/5816f3fd2f301e005ce3f811
 * RxBus.getInstance().send(BusEvents.TAP, "Tap传了一个String");
 *  RxBus.getInstance().send(BusEvents.OTHER, new OtherEvent("Cloud", 25));
 *   RxBus.bind(this)
    .setEventCode(BusEvents.TAP)
     // .setEndLifeEvent(FragmentEvent.DESTROY_VIEW) //不设置默认与fragment生命周期同步
     .onNext(new Action1<BusEvents<?>>() {
        @Override public void call(BusEvents<?> events) {
        String content = events.getContent();
        textView.setText(content);
        }
    })
 .create();

     RxBus.bind(this)
     .setEventCode(BusEvents.OTHER)
     .setEndLifeEvent(FragmentEvent.DESTROY_VIEW) //不设置默认与fragment生命周期同步
     .onNext(new Action1<BusEvents<?>>() {
        @Override public void call(BusEvents<?> events) {
        OtherEvent eventCode = events.getContent();
        textView.setText("Name: "  + eventCode.getName() + ",Age: "+ eventCode.getAge());
        }
    })
     .onError(new Action1<Throwable>() {
    @Override public void call(Throwable throwable) {
        textView.setText(throwable.toString());
    }
    }) // 异常处理，默认捕获异常，不做处理，程序不会crash。
     .create();
 *
 */
public class RxBus {

    private static RxBus rxBus;
    private final Subject<BusEvents<?>, BusEvents<?>> busEventsSerializedSubject = new SerializedSubject<>(PublishSubject.<BusEvents<?>>create());

    private RxBus() {
    }

    public static RxBus getInstance() {
        if (rxBus == null) {
            synchronized (RxBus.class) {
                if (rxBus == null) {
                    rxBus = new RxBus();
                }
            }
        }
        return rxBus;
    }

    public void send(BusEvents<?> o) {
        busEventsSerializedSubject.onNext(o);
    }

    public void send(@BusEvents.EventCode int code, Object content) {
        BusEvents<Object> event = new BusEvents<>();
        event.code = code;
        event.content = content;
        send(event);
    }

    public Observable<BusEvents<?>> toObservable() {
        return busEventsSerializedSubject;
    }

    public static SubscriberBuilder bind(FragmentLifecycleProvider provider) {
        return new SubscriberBuilder(provider);
    }

    public static SubscriberBuilder bind(ActivityLifecycleProvider provider) {
        return new SubscriberBuilder(provider);
    }


    public static class SubscriberBuilder {

        private FragmentLifecycleProvider mFragLifecycleProvider;
        private ActivityLifecycleProvider mActLifecycleProvider;
        private FragmentEvent mFragmentEndEvent;
        private ActivityEvent mActivityEndEvent;
        private int eventCode;
        private Action1<? super BusEvents<?>> onNext;
        private Action1<Throwable> onError;

        public SubscriberBuilder(FragmentLifecycleProvider provider) {
            this.mFragLifecycleProvider = provider;
        }

        public SubscriberBuilder(ActivityLifecycleProvider provider) {
            this.mActLifecycleProvider = provider;
        }

        public SubscriberBuilder setEventCode(@BusEvents.EventCode int eventCode) {
            this.eventCode = eventCode;
            return this;
        }

        public SubscriberBuilder setEndLifeEvent(FragmentEvent event) {
            this.mFragmentEndEvent = event;
            return this;
        }

        public SubscriberBuilder setEndLifeEvent(ActivityEvent event) {
            this.mActivityEndEvent = event;
            return this;
        }

        public SubscriberBuilder onNext(Action1<? super BusEvents<?>> action) {
            this.onNext = action;
            return this;
        }

        public SubscriberBuilder onError(Action1<Throwable> action) {
            this.onError = action;
            return this;
        }


        public void create() {
            init();
        }

        private Subscription init() {
            if (mFragLifecycleProvider != null) {
                return RxBus.getInstance().toObservable()
                        .compose(mFragmentEndEvent == null ? mFragLifecycleProvider.<BusEvents<?>>bindToLifecycle() : mFragLifecycleProvider.<BusEvents<?>>bindUntilEvent(mFragmentEndEvent)) // 绑定生命周期
                        .filter(new Func1<BusEvents<?>, Boolean>() {
                            @Override
                            public Boolean call(BusEvents<?> events) {
                                return events.code == eventCode;
                            }
                        })   //过滤 根据code判断返回事件
                        .subscribe(onNext, onError == null ? new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                throwable.printStackTrace();
                            }
                        } : onError);
            }
            if (mActLifecycleProvider != null) {
                return RxBus.getInstance().toObservable()
                        .compose(mActivityEndEvent == null ? mActLifecycleProvider.<BusEvents<?>>bindToLifecycle() : mActLifecycleProvider.<BusEvents<?>>bindUntilEvent(mActivityEndEvent))
                        .filter(new Func1<BusEvents<?>, Boolean>() {
                            @Override
                            public Boolean call(BusEvents<?> events) {
                                return events.code == eventCode;
                            }
                        })
                        .subscribe(onNext, onError == null ? new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                throwable.printStackTrace();
                            }
                        } : onError);
            }
            return null;
        }
    }
}
