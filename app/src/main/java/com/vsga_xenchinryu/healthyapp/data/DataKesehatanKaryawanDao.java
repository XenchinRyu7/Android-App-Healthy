package com.vsga_xenchinryu.healthyapp.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.vsga_xenchinryu.healthyapp.data.DataKondisiKaryawan;

import java.util.List;

@Dao
public interface DataKesehatanKaryawanDao {

    @Insert
    void insertDataKondisiKaryawan(DataKondisiKaryawan dataKondisiKaryawan);

    @Update
    void updateDataKondisiKaryawan(DataKondisiKaryawan dataKondisiKaryawan);

    @Delete
    void deleteDataKondisiKaryawan(DataKondisiKaryawan dataKondisiKaryawan);

    @Query("SELECT * FROM dataKondisiKaryawan WHERE idKaryawan = :idKaryawan")
    List<DataKondisiKaryawan> getDataKondisiKaryawanByKaryawan(long idKaryawan);

}
