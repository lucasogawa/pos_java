package com.ogawalucas.automobilesupplycontrol.automobile.adapter;

import java.util.List;

public class AutomobileAdapterView {

    private String nickname;
    private List<AvgSupply> avgSupplies;

    public AutomobileAdapterView() {

    }

    public AutomobileAdapterView(String nickname, List<AvgSupply> avgSupplies) {
        this.nickname = nickname;
        this.avgSupplies = avgSupplies;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<AvgSupply> getAvgSupplies() {
        return avgSupplies;
    }

    public void setAvgSupplies(List<AvgSupply> avgSupplies) {
        this.avgSupplies = avgSupplies;
    }
}
