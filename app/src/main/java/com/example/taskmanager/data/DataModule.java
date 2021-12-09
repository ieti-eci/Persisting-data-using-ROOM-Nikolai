package com.example.taskmanager.data;

import android.content.Context;

import androidx.room.Room;

import com.example.taskmanager.analytics.AnalyticsAdapter;
import com.example.taskmanager.analytics.GoogleAnalytics;
import com.example.taskmanager.model.AppDatabase;
import com.example.taskmanager.model.dao.UserDao;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DataModule {

    @Provides
    @Singleton
    public AnalyticsAdapter provideAnalyticsAdapter() {

        return new GoogleAnalytics();
    }

    @Provides
    @Singleton
    public Executor provideExecutor() {

        return Executors.newFixedThreadPool(1);
    }

    @Provides
    @Singleton
    public AppDatabase provideAppDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "data_base_room").build();
    }

    @Provides
    @Singleton
    public UserDao provideUserDao(AppDatabase appDatabase) {
        return appDatabase.userDao();
    }

}
