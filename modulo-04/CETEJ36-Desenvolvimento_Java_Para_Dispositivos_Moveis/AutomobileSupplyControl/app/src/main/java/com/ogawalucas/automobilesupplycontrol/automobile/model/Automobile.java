package com.ogawalucas.automobilesupplycontrol.automobile.model;

import android.content.Context;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.ogawalucas.automobilesupplycontrol.R;

@Entity(
    tableName = "automobile",
    indices = @Index(value = {"nickname"}, unique = true)
)
public class Automobile {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String nickname;

    private boolean travel;

    private String type;

    private String brand;

    private String model;

    private String color;

    private String manufactoringYear;

    public Automobile(
        String nickname,
        boolean travel,
        String type,
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

    public String getType() {
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

    public void setType(String type) {
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
