package com.example.mod5.layanan;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Layanan {

    @PrimaryKey(autoGenerate = true)
    public int idLayanan;

    @ColumnInfo(name = "namaTempat")
    public String namaTempat;

    @ColumnInfo(name = "deskripsi")
    public String deskripsi;

    @ColumnInfo(name = "noTelp")
    public String noTelp;

    @ColumnInfo(name = "alamat")
    public String alamat;

    @ColumnInfo(name = "fotoTempat")
    public String fotoTempat;

    @ColumnInfo(name = "idKodeLayanan")
    public String idKodeLayanan;

    public int getIdLayanan() {
        return idLayanan;
    }

    public void setIdLayanan(int idLayanan) {
        this.idLayanan = idLayanan;
    }

    public String getNamaTempat() {
        return namaTempat;
    }

    public void setNamaTempat(String namaTempat) {
        this.namaTempat = namaTempat;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getFotoTempat() {
        return fotoTempat;
    }

    public void setFotoTempat(String fotoTempat) {
        this.fotoTempat = fotoTempat;
    }

    public String getIdKodeLayanan() {
        return idKodeLayanan;
    }

    public void setIdKodeLayanan(String idKodeLayanan) {
        this.idKodeLayanan = idKodeLayanan;
    }
}
