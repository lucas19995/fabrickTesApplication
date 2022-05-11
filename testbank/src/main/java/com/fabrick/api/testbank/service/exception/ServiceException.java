package com.fabrick.api.testbank.service.exception;

import com.fabrick.api.testbank.model.object.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.message.Message;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

public class ServiceException extends Exception {
    private HttpStatus httpStatus;
    protected String code;
    protected String codeEx;
    protected String description;

    public ServiceException(Exception e, List<String> errorCode, String accountId) {
        super(e.getMessage(),e);

        code = "-1";
        httpStatus = HttpStatus.BAD_GATEWAY;
        initHttpStatus(e, errorCode, accountId);
    }

    private void initHttpStatus(Exception e, List<String> errorCode, String accountId) {
        if(!(e instanceof HttpClientErrorException)) return;
        HttpClientErrorException ex = (HttpClientErrorException) e;
        setHttpStatus(ex.getStatusCode());
        if(errorCode.contains("REQ010")) {
            code = "API000";
            description = "Errore tecnico la condizione BP049 non è prevista per il conto id ".concat(accountId);
        }

    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getCodeEx() {
        return codeEx;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}

