package com.vsga_xenchinryu.healthyapp.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "dataInformasiKaryawan")
public class DataKaryawan {

    @PrimaryKey(autoGenerate = true)
    long idKaryawan;
    String namaKaryawan;
    String jenisKelamin;
    String jabatanKaryawan;
    String alamatKaryawan;
    String noTelepon;
    String waktuPencatatan;

    public DataKaryawan(long idKaryawan, String namaKaryawan, String jenisKelamin, String jabatanKaryawan, String alamatKaryawan, String noTelepon, String waktuPencatatan) {
        this.idKaryawan = idKaryawan;
        this.namaKaryawan = namaKaryawan;
        this.jenisKelamin = jenisKelamin;
        this.jabatanKaryawan = jabatanKaryawan;
        this.alamatKaryawan = alamatKaryawan;
        this.noTelepon = noTelepon;
        this.waktuPencatatan = waktuPencatatan;
    }

    public long getIdKaryawan() {
        return idKaryawan;
    }

    public String getNamaKaryawan() {
        return namaKaryawan;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public String getJabatanKaryawan() {
        return jabatanKaryawan;
    }

    public String getAlamatKaryawan() {
        return alamatKaryawan;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public String getWaktuPencatatan() {
        return waktuPencatatan;
    }

    public void setNamaKaryawan(String namaKaryawan) {
        this.namaKaryawan = namaKaryawan;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public void setJabatanKaryawan(String jabatanKaryawan) {
        this.jabatanKaryawan = jabatanKaryawan;
    }

    public void setAlamatKaryawan(String alamatKaryawan) {
        this.alamatKaryawan = alamatKaryawan;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    public void setWaktuPencatatan(String waktuPencatatan) {
        this.waktuPencatatan = waktuPencatatan;
    }


}
