package com.ogawalucas.automobilesupplycontrol.automobile.adapter;

public class AvgSupply {

    private String typeOfFuel;
    private double avgKilometersPerLiter;

    public AvgSupply() {

    }

    public AvgSupply(String typeOfFuel, double avgKilometersPerLiter) {
        this.typeOfFuel = typeOfFuel;
        this.avgKilometersPerLiter = avgKilometersPerLiter;
    }

    public String getTypeOfFuel() {
        return typeOfFuel;
    }

    public void setTypeOfFuel(String typeOfFuel) {
        this.typeOfFuel = typeOfFuel;
    }

    public double getAvgKilometersPerLiter() {
        return avgKilometersPerLiter;
    }

    public void setAvgKilometersPerLiter(double avgKilometersPerLiter) {
        this.avgKilometersPerLiter = avgKilometersPerLiter;
    }
}
