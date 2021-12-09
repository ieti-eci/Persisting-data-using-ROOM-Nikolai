package com.example.taskmanager.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.taskmanager.mappers.TimeMapper;
import com.example.taskmanager.model.dao.UserDao;

@Database(entities = {User.class}, version = 1)
@TypeConverters({TimeMapper.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();

}
