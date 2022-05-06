package com.ogawalucas.cities.modules.cities.dto;

import com.ogawalucas.cities.modules.cities.entities.Citie;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public final class CitieDto {

    @NotBlank(message = "{app.name.blank}")
    @Size(min = 5, max = 60, message = "{app.name.size}")
    private String name;

    @NotBlank(message = "{app.state.blank}")
    @Size(min = 2, max = 2, message = "{app.state.size}")
    private String state;

    public CitieDto(String name, String state) {
        this.name = name;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public Citie convertToEntity() {
        var citie = new Citie();

        citie.setName(this.name);
        citie.setState(this.state);

        return citie;
    }
}
