package ru.zemga.mobile.android.logic;

import java.util.HashMap;
import java.util.List;

import ru.zemga.mobile.android.model.Land;

public class RepositoryLandMessage implements RepositoryMessage<HashMap<Long, Land>>{

    private final String status;
    private final HashMap<Long, Land> lands;

    public RepositoryLandMessage(String status, HashMap<Long, Land> lands)
    {
        this.status = status;
        this.lands = lands;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public HashMap<Long, Land> getData() {
        return lands;
    }
}
