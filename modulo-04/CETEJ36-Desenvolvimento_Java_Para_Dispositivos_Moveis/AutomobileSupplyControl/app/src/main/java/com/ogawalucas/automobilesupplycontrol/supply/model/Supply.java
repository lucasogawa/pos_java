package com.ogawalucas.automobilesupplycontrol.supply.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.ogawalucas.automobilesupplycontrol.automobile.model.Automobile;

@Entity(tableName = "supply",
    foreignKeys = @ForeignKey(
        entity = Automobile.class,
        parentColumns = "id",
        childColumns = "automobileId"
    )
)
public class Supply {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(index = true)
    private long automobileId;

    private String fuelStation;

    private String date;

    private String typeOfFuel;

    private String kilometers;

    private String liters;

    private String amountPaid;

    public Supply(long automobileId, String fuelStation, String date, String typeOfFuel, String kilometers, String liters, String amountPaid) {
        this.automobileId = automobileId;
        this.fuelStation = fuelStation;
        this.date = date;
        this.typeOfFuel = typeOfFuel;
        this.kilometers = kilometers;
        this.liters = liters;
        this.amountPaid = amountPaid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAutomobileId() {
        return automobileId;
    }

    public void setAutomobileId(int automobileId) {
        this.automobileId = automobileId;
    }

    public String getFuelStation() {
        return fuelStation;
    }

    public void setFuelStation(String fuelStation) {
        this.fuelStation = fuelStation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTypeOfFuel() {
        return typeOfFuel;
    }

    public void setTypeOfFuel(String typeOfFuel) {
        this.typeOfFuel = typeOfFuel;
    }

    public String getKilometers() {
        return kilometers;
    }

    public void setKilometers(String kilometers) {
        this.kilometers = kilometers;
    }

    public String getLiters() {
        return liters;
    }

    public void setLiters(String liters) {
        this.liters = liters;
    }

    public String getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
    }
}

