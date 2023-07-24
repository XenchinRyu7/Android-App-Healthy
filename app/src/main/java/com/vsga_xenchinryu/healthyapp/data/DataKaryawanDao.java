package com.vsga_xenchinryu.healthyapp.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DataKaryawanDao {
    @Insert
    void insert(DataKaryawan data);

    @Update
    void update(DataKaryawan data);

    @Delete
    void delete(DataKaryawan data);

    @Query("SELECT * FROM dataInformasiKaryawan")
    List<DataKaryawan> getAllData();

    @Query("SELECT * FROM dataInformasiKaryawan WHERE idKaryawan = :idKaryawan")
    DataKaryawan getDataById(long idKaryawan);
}
