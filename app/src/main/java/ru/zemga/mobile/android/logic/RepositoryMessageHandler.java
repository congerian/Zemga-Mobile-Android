package ru.zemga.mobile.android.logic;

public interface RepositoryMessageHandler {
    public void onMessageReceive(RepositoryMessage<?> message);
}
