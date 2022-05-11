package com.fabrick.api.testbank.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TypeDTO {

    @JsonProperty("enumeration")
    private String enumaration;
    @JsonProperty("value")
    private String value;

    public String getEnumaration() {
        return enumaration;
    }

    public void setEnumaration(String enumaration) {
        this.enumaration = enumaration;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
