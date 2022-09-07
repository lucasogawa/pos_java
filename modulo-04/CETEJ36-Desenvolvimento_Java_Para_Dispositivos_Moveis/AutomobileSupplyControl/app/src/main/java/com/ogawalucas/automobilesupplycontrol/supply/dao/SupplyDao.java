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

    @Query("SELECT * FROM Supply WHERE automobileId = :automobileId")
    List<Supply> findByAutomobileId(long automobileId);

    @Query("SELECT * FROM Supply WHERE automobileId = :automobileId ORDER BY date ASC")
    List<Supply> findByAutomobileIdOrderByDateAsc(long automobileId);

    @Query("SELECT * FROM Supply WHERE automobileId = :automobileId ORDER BY date DESC")
    List<Supply> findByAutomobileIdOrderByDateDesc(long automobileId);

    @Query("SELECT DISTINCT(typeOfFuel) FROM Supply WHERE automobileId = :automobileId ORDER BY typeOfFuel")
    List<String> findTypesOfFuelByAutomobileId(long automobileId);

    @Query("SELECT avg(kilometersPerLiter) FROM Supply WHERE automobileId = :automobileId AND typeOfFuel = :typeOfFuel")
    double findAvgKilometersPerLiterByAutomobileIdAndTypeOfFuel(long automobileId, String typeOfFuel);
}
