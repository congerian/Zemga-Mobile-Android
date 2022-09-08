package ru.zemga.mobile.android.logic;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import ru.zemga.mobile.android.R;

public class LogicMain
{
    @SuppressLint("StaticFieldLeak")
    private static volatile LogicMain instance;

    private final static Object mutex = new Object();

    private final Context context;
    private final LogicThread logicThread;

    public static LogicMain getInstance(Context context)
    {
        LogicMain result = instance;
        if (result == null)
        {
            synchronized (mutex)
            {
                result = instance;
                if (result == null) instance = result = new LogicMain(context);
            }
        }
        return result;
    }

    private class LogicThread extends Thread
    {
        @Override
        public void run()
        {
            while (true)
            {
                Log.i(context.getResources().getString(R.string.app_name), "Still working...");
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private LogicMain(Context context)
    {
        if (!(context instanceof Application))
            throw new Error("LogicMain can only be instantiated using Application context!");
        this.context = context;
        this.logicThread = new LogicThread();

        logicThread.start();
    }
}
