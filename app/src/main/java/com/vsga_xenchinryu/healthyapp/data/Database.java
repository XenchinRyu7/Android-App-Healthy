package com.vsga_xenchinryu.healthyapp.data;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {DataLogin.class, DataKaryawan.class, DataKondisiKaryawan.class}, version = 1)
public abstract class Database extends RoomDatabase {

    public abstract DataKesehatanKaryawanDao getDataKesehatanKaryawanDao();
    public abstract DataLoginDao getDataLoginDao();
    public abstract DataKaryawanDao getDataKaryawanDao();
    private static Database INSTANCE = null;

    public static Database getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            Database.class,
                            "Healthy.db")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
