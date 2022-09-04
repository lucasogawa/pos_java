package com.ogawalucas.automobilesupplycontrol.supply.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ogawalucas.automobilesupplycontrol.supply.model.Supply;

import java.util.List;

@Dao
public interface SupplyDao {

    @Insert
    long create(Supply supply);

    @Delete
    void delete(Supply supply);

    @Update
    void update(Supply supply);

    @Query("SELECT * FROM Supply WHERE id = :id")
    Supply findById(long id);

    @Query("SELECT * FROM Supply ORDER BY date ASC")
    List<Supply> findAllOrderByDateAsc();

    @Query("SELECT * FROM Supply ORDER BY date DESC")
    List<Supply> findAllOrderByDateDesc();
}
