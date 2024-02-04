package com.wj.learnmvi.point;

import android.util.Log;

import androidx.annotation.NonNull;

import com.wdt.reporter.IReporter;
import com.wdt.reporter.param.Parameter;

/**
 * @author longxiang.bao
 * @date 2021/10/21
 */
public class JavaReporter implements IReporter {
    @Override
    public void report(@NonNull Parameter parameter) {

        Log.d("java event name -> ", parameter.getEventName());

        Log.d("java event action -> ", parameter.getEventAction());

        Log.d("java event map  -> ", parameter.getEventMap().toString());
    }
}
