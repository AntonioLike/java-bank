package org.academiadecodigo.javabank.persistence;

public interface SessionManager<T> {

    void startSession();

    void stopSession();

    T getCurrentSession();

}
