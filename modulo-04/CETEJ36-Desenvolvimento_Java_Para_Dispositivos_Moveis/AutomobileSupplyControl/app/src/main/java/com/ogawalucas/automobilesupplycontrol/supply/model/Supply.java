package com.ogawalucas.automobilesupplycontrol.supply.model;

import static com.ogawalucas.automobilesupplycontrol.utils.NumberUtils.roundDouble;

import android.content.Context;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.ogawalucas.automobilesupplycontrol.automobile.model.Automobile;
import com.ogawalucas.automobilesupplycontrol.utils.DateUtils;

import java.util.Date;

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

    private Date date;

    private String typeOfFuel;

    private double kilometers;

    private double liters;

    private double amountPaid;

    private double pricePerLiter;

    private double kilometersPerLiter;

    public Supply(
        long automobileId,
        String fuelStation,
        Date date,
        String typeOfFuel,
        double kilometers,
        double liters,
        double amountPaid
    ) {
        this.automobileId = automobileId;
        this.fuelStation = fuelStation;
        this.date = date;
        this.typeOfFuel = typeOfFuel;
        this.kilometers = kilometers;
        this.liters = liters;
        this.amountPaid = amountPaid;
        this.pricePerLiter = calculatePricePerLiter();
        this.kilometersPerLiter = calculateKilometersPerLiter();
    }

    private double calculatePricePerLiter() {
        return roundDouble(this.amountPaid / this.liters);
    }

    private double calculateKilometersPerLiter() {
        return roundDouble(this.kilometers / this.liters);
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTypeOfFuel() {
        return typeOfFuel;
    }

    public void setTypeOfFuel(String typeOfFuel) {
        this.typeOfFuel = typeOfFuel;
    }

    public double getKilometers() {
        return kilometers;
    }

    public void setKilometers(double kilometers) {
        this.kilometers = kilometers;
    }

    public double getLiters() {
        return liters;
    }

    public void setLiters(double liters) {
        this.liters = liters;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public double getPricePerLiter() {
        return pricePerLiter;
    }

    public void setPricePerLiter(double pricePerLiter) {
        this.pricePerLiter = pricePerLiter;
    }

    public double getKilometersPerLiter() {
        return kilometersPerLiter;
    }

    public void setKilometersPerLiter(double kilometersPerLiter) {
        this.kilometersPerLiter = kilometersPerLiter;
    }

    public String getFormattedDate(Context context) {
        return DateUtils.format(context, this.date);
    }
}

