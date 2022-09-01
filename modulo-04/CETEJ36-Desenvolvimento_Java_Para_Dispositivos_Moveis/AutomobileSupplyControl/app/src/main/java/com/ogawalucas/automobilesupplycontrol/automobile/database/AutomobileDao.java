package com.ogawalucas.automobilesupplycontrol.automobile.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ogawalucas.automobilesupplycontrol.automobile.model.Automobile;

import java.util.List;

@Dao
public interface AutomobileDao {

    @Insert
    long create(Automobile automobile);

    @Delete
    void delete(Automobile automobile);

    @Update
    void update(Automobile automobile);

    @Query("SELECT * FROM automobile WHERE id = :id")
    Automobile findById(long id);

    @Query("SELECT * FROM automobile")
    List<Automobile> findAll();
}
