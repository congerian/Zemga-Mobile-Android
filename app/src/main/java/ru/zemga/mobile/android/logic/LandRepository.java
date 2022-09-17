package ru.zemga.mobile.android.logic;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.locks.ReentrantLock;

public class LandRepository
{
    private final ReentrantLock handlerMutex   = new ReentrantLock();
    private final ReentrantLock dataMutex      = new ReentrantLock();
    private List<WeakReference<RepositoryMessageHandler>> handlerList;

    public LandRepository ()
    {
        handlerMutex.lock();
        handlerList = new LinkedList<>();
        handlerMutex.unlock();
    }

    public void applySubcription (RepositoryMessageHandler handler)
    {
        handlerMutex.lock();
        handlerList.add(new WeakReference<>(handler));
        handlerMutex.unlock();
    }

    private void sendMessages ()
    {
        handlerMutex.lock();
        dataMutex.lock();

        ListIterator<WeakReference<RepositoryMessageHandler>> iterator = handlerList.listIterator();

        while (iterator.hasNext())
        {
            RepositoryMessageHandler handler = iterator.next().get();
            if (handler == null) iterator.remove();
            else
            {
                // TODO:
                //  Отправляем сообщение
            }
        }

        handlerMutex.unlock();
        dataMutex.unlock();
    }
}
