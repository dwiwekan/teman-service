package com.example.mod5.layanan;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LayananDao {

    @Query("SELECT * FROM layanan")
    List<Layanan>getAllLayanan();

    @Insert
    Void insertLayanan(Layanan... layanan);

    @Delete
    Void delete(Layanan layanan);

}

