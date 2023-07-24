package com.vsga_xenchinryu.healthyapp.data;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.vsga_xenchinryu.healthyapp.data.DataKaryawan;

@Entity(tableName = "dataKondisiKaryawan",
        foreignKeys = @ForeignKey(entity = DataKaryawan.class,
                parentColumns = "idKaryawan",
                childColumns = "idKaryawan",
                onDelete = ForeignKey.CASCADE))
public class DataKondisiKaryawan {

    @PrimaryKey(autoGenerate = true)
    private long idKesehatan;

    private long idKaryawan; // Foreign key ke tabel dataInformasiKaryawan
    private float temperature;
    private String bloodPressure;
    private String healthHistory;
    private String symptoms;
    private String timestamp;

    public DataKondisiKaryawan(long idKaryawan, float temperature, String bloodPressure,
                               String healthHistory, String symptoms, String timestamp) {
        this.idKaryawan = idKaryawan;
        this.temperature = temperature;
        this.bloodPressure = bloodPressure;
        this.healthHistory = healthHistory;
        this.symptoms = symptoms;
        this.timestamp = timestamp;
    }

    public long getIdKesehatan() {
        return idKesehatan;
    }

    public void setIdKesehatan(long idKesehatan) {
        this.idKesehatan = idKesehatan;
    }

    public long getIdKaryawan() {
        return idKaryawan;
    }

    public void setIdKaryawan(long idKaryawan) {
        this.idKaryawan = idKaryawan;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getHealthHistory() {
        return healthHistory;
    }

    public void setHealthHistory(String healthHistory) {
        this.healthHistory = healthHistory;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
