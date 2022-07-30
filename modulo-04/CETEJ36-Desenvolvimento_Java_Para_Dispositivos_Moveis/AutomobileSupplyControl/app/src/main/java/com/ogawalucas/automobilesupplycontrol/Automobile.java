package com.ogawalucas.automobilesupplycontrol;

public class Automobile {

    private String nickname;
    private String type;
    private String brand;
    private String model;
    private String color;
    private String manufactoringYear;

    public Automobile() {

    }

    public Automobile(String nickname, String type, String brand, String model, String color, String manufactoringYear) {
        this.nickname = nickname;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.manufactoringYear = manufactoringYear;
    }

    public String getNickname() {
        return nickname;
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

    @Override
    public String toString() {
        return String.format(
            "%s - %s - %s - %s - %s - %s",
            this.nickname, this.type, this.brand, this.model, this.color, this.manufactoringYear
        );
    }
}
