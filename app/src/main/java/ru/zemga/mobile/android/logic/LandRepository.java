package ru.zemga.mobile.android.logic;

import android.content.res.Resources;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.locks.ReentrantLock;

import ru.zemga.mobile.android.R;
import ru.zemga.mobile.android.model.Land;

public class LandRepository
{
    private static class Response
    {
        public List<Land> lands;
    }

    private final ReentrantLock handlerMutex   = new ReentrantLock();
    private final ReentrantLock dataMutex      = new ReentrantLock();

    private final List<WeakReference<RepositoryMessageHandler>> handlerList;
    private volatile boolean isUpdating = false;
    private volatile HashMap<Long, Land> lands;

    private final String serverURL;
    private final String serverPort;
    private final String rest_client_get_all;
    private final boolean isHTTPS;

    public LandRepository (Resources res)
    {
        this (
            res.getString(R.string.server_url),
            res.getString(R.string.server_port),
            res.getString(R.string.server_rest_client_get_all),
            res.getBoolean(R.bool.server_isHTTPS)
        );

    }

    public LandRepository
            (
                    String serverURL,
                    String serverPort,
                    String rest_client_get_all,
                    boolean isHTTPS
    )
    {
        handlerList = new LinkedList<>();

        this.serverURL = serverURL;
        this.serverPort = serverPort;
        this.rest_client_get_all = rest_client_get_all;
        this.isHTTPS = isHTTPS;

        requestUpdate();
    }

    public void applySubscription (RepositoryMessageHandler handler)
    {
        handlerMutex.lock();
        handlerList.add(new WeakReference<>(handler));
        handlerMutex.unlock();
    }

    public void requestUpdate ()
    {
        if (!isUpdating) update();
    }

    private void update ()
    {
        isUpdating = true;

        new Thread(() -> {
            try
            {
                String urlPrefix = isHTTPS ? "https://" : "http://";
                URL url = new URL(urlPrefix + serverURL + ":" + serverPort + "/" + rest_client_get_all);
                System.out.println(url);

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(15000);
                urlConnection.setDoOutput(true);
                urlConnection.connect();

                BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder sb = new StringBuilder();

                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                br.close();

                String jsonString = sb.toString();
                System.out.println("JSON: " + jsonString);

                Gson gson = new Gson();
                Response responseLands = gson.fromJson(jsonString, Response.class);

                dataMutex.lock();
                lands = new HashMap<>();

                for(Land land : responseLands.lands) {
                    lands.put(land.getFields().getId(), land);
                }
                dataMutex.unlock();

                sendMessages();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                isUpdating = false;

            }
        }).start();


    }

    public Land getLand(Long id)
    {
        dataMutex.lock();
        Land land = lands.get(id);
        dataMutex.unlock();
        return land;
    }

    public HashMap<Long, Land> getLands()
    {
        dataMutex.lock();
        HashMap<Long, Land> lands_copy = new HashMap<> (this.lands);
        dataMutex.unlock();
        return lands_copy;
    }

    private void sendMessages ()
    {
        handlerMutex.lock();
        dataMutex.lock();

        ListIterator<WeakReference<RepositoryMessageHandler>> iterator
                = handlerList.listIterator();

        while (iterator.hasNext())
        {
            RepositoryMessageHandler handler = iterator.next().get();
            if (handler == null) iterator.remove();
            else
            {
                handler.onMessageReceive(new RepositoryLandMessage("OK", new HashMap<>(lands)));
            }
        }

        handlerMutex.unlock();
        dataMutex.unlock();
    }
}
