package com.example.taskmanager.network.storage;

/**
 * Storage signature
 *
 * @author 
 */
public interface Storage {

    void saveToken(String token);

    String getToken();

}
