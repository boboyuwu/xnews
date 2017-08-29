package com.boboyuwu.common.util;


import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * RxBus类：
 *   用来解耦组件通信，替代EventBus
 *
 * 使用方式：
 *    （1）注册：
 1     Observable<String> addOb = RxBus.get()
 2                 .register("addFeedTag", String.class);
 3
 4     addOb.observeOn(AndroidSchedulers.mainThread())
 5                 .subscribe(s -> {
 6                     // todo: Accept event and process here
 7                 });
 *    （2）接收：
 *     RxBus.get().post("addFeedTag", "hello RxBus!");
 *    （3）反注册：
 *     RxBus.get().unregister("addFeedTag", addOb);
 *
 *     增加Stick特性
 *
 * Created by wubo on 2016/12/22.
 */
public class RxBus {

    private static final String TAG   = RxBus.class.getSimpleName();
    private static Object async = new Object();
    private static RxBus        instance;
    public  static boolean      DEBUG = false;

    private ConcurrentHashMap<Object, List<Subject>> subjectMapper = new ConcurrentHashMap<>();

    private RxBus() {}

    public static RxBus get() {
        synchronized (async){
            if (null == instance)
                instance = new RxBus();
            return instance;
        }
    }

    /**
     * 注册事件
     * @param tag
     * @param clazz
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> Observable<T> register(@NonNull Object tag, @NonNull Class<T> clazz) {
        List<Subject> subjectList = subjectMapper.get(tag);
        if (null == subjectList) {
            subjectList = new ArrayList<>();
            subjectMapper.put(tag, subjectList);
        }
        Subject<T> subject;
        subjectList.add(subject = PublishSubject.create());
        if (DEBUG) Log.d(TAG, "[register]subjectMapper: " + subjectMapper);
        return subject;
    }

    /**
     * 反注册事件
     * @param tag
     * @param observable
     */
    public void unregister(@NonNull Object tag, @NonNull Observable observable) {
        List<Subject> subjects = subjectMapper.get(tag);
        if (null != subjects) {
            subjects.remove(observable);
            if (isEmpty(subjects)) {
                subjectMapper.remove(tag);
            }
        }
        if (DEBUG) Log.d(TAG, "[unregister]subjectMapper: " + subjectMapper);
    }

    /**
     * 清空所有
     */
    public void clear(){
        if (subjectMapper != null)
            subjectMapper.clear();
    }

    /**
     * 发送事件
     * @param content
     */
    public void post(@NonNull Object content) {
        post(content.getClass().getName(), content);
    }

    /**
     * 发送事件
     * @param tag
     * @param content
     */
    @SuppressWarnings("unchecked")
    public void post(@NonNull Object tag, @NonNull Object content) {
        List<Subject> subjectList = subjectMapper.get(tag);
        if (!isEmpty(subjectList)) {
            for (Subject subject : subjectList) {
                subject.onNext(content);
            }
        }
        if (DEBUG) Log.d(TAG, "[send]subjectMapper: " + subjectMapper);
    }

    private boolean isEmpty(List<Subject> subjectList) {
        return (subjectList == null || subjectList.size() == 0);
    }



}