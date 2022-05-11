package com.fabrick.api.testbank.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MoneyTransferDTO {

    private String moneyTransferId;
    private String status;
    private String direction;
}
