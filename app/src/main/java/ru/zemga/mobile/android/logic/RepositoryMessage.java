package ru.zemga.mobile.android.logic;

public interface RepositoryMessage<T> {
    public String getStatus();
    public T getData();
}
