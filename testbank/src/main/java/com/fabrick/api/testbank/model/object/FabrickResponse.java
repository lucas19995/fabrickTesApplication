package com.fabrick.api.testbank.model.object;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FabrickResponse<T> {

    @JsonProperty("status")
    public StatusCode status;
    @JsonAlias({"error", "errors"})
    public List<ErrorResponse> errors;
    @JsonProperty("payload")
    public T payload;

}
