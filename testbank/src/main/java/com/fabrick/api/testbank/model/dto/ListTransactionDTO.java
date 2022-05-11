package com.fabrick.api.testbank.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListTransactionDTO {

    @JsonProperty("list")
    private List<TransactionDTO> list;

    public List<TransactionDTO> getList() {
        return list;
    }

    public void setList(List<TransactionDTO> list) {
        this.list = list;
    }
}
