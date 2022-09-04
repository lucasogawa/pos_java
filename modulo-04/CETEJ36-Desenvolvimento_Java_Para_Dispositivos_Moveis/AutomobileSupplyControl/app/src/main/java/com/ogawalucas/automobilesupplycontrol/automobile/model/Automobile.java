package com.ogawalucas.automobilesupplycontrol.automobile.model;

import android.content.Context;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.ogawalucas.automobilesupplycontrol.R;

@Entity(tableName = "automobile")
public class Automobile {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String nickname;

    private boolean travel;

    private EType type;

    private String brand;

    private String model;

    private String color;

    private String manufactoringYear;

    public Automobile(
        String nickname,
        boolean travel,
        EType type,
        String brand,
        String model,
        String color,
        String manufactoringYear
    ) {
        this.nickname = nickname;
        this.travel = travel;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.manufactoringYear = manufactoringYear;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public boolean isTravel() {
        return travel;
    }

    public EType getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public String getManufactoringYear() {
        return manufactoringYear;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setTravel(boolean travel) {
        this.travel = travel;
    }

    public void setType(EType type) {
        this.type = type;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setManufactoringYear(String manufactoringYear) {
        this.manufactoringYear = manufactoringYear;
    }

    public String toStringTravelCal(Context context) {
        return this.travel
            ? context.getString(R.string.travel_car)
            : context.getString(R.string.city_car);
    }

    @Override
    public String toString() {
        return this.nickname;
    }
}
