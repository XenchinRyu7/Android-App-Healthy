package com.vsga_xenchinryu.healthyapp.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DataLoginDao {
    @Insert
    void insert(DataLogin data);

    @Update
    void update(DataLogin data);

    @Delete
    void delete(DataLogin data);

    @Query("SELECT * FROM login")
    List<DataLogin> getDataLogin();
}
