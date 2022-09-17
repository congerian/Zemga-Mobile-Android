package ru.zemga.mobile.android.logic;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.util.concurrent.locks.ReentrantLock;

import ru.zemga.mobile.android.R;

public class LogicMain
{
    @SuppressLint("StaticFieldLeak")
    private static volatile LogicMain instance;

    private final static ReentrantLock instanceMutex = new ReentrantLock();

    private final Context context;
    private final LogicThread logicThread;

    private volatile LandRepository landRepository;

    public static LogicMain getInstance(Context context)
    {
        LogicMain result = instance;
        if (result == null)
        {
            instanceMutex.lock();
            result = instance;
            if (result == null) instance = result = new LogicMain(context);
            instanceMutex.unlock();
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
                try
                {
                    Thread.sleep(2000L);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    private LogicMain(Context context)
    {
        if (!(context instanceof Application))
        {
            throw new Error("LogicMain can only be instantiated using Application context!");
        }

        this.landRepository = new LandRepository();
        this.context = context;
        this.logicThread = new LogicThread();

        logicThread.start();
    }
}
