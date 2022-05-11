package com.fabrick.api.testbank.service.exception;

import com.fabrick.api.testbank.model.object.ErrorResponse;
import com.fabrick.api.testbank.model.object.ErrorWrapper;
import org.springframework.util.StringUtils;

public class ExceptionMapper {

    public static ErrorWrapper map(Exception e) {
        return map("EXCEPTION001", e);
    }

    public static ErrorWrapper map(String code, Exception e) {

        ErrorWrapper errorWrapper = new ErrorWrapper();
        errorWrapper.response = new ErrorResponse();
        errorWrapper.response.setCode(code);
        errorWrapper.response.setDescription(e.getMessage());

        if(e instanceof ServiceException) {
            ServiceException exc = (ServiceException) e;
            errorWrapper.response.setCode(exc.getCode());
            if(exc.getDescription() != null){
                errorWrapper.response.setDescription(exc.getDescription());
            }
            errorWrapper.responseStatus = ((ServiceException) e).getHttpStatus()!=null ? ((ServiceException) e).getHttpStatus().value() : 0;
        }

        return errorWrapper;
    }
}
