package com.example.mod5.layanan;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM User WHERE telp = :telp AND password = :password")
    List<User> login(String telp, String password);

    //buat get user by id
    @Query("SELECT * FROM User WHERE id = :id")
    User getUserbyid(int id);

    //buat register user
    @Insert
    long[] RegisterUser(User... users);
}
