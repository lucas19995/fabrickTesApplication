package com.fabrick.api.testbank.model.object;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ErrorWrapper {

    @JsonIgnore
    public int responseStatus = 0;
    public ErrorResponse response;

}
