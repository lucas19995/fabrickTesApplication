package com.fabrick.api.testbank.controller.impl;

import com.fabrick.api.testbank.controller.FabrickController;
import com.fabrick.api.testbank.model.dto.CreditorDTO;
import com.fabrick.api.testbank.model.object.AccountCashResponse;
import com.fabrick.api.testbank.model.object.ListTransactionResponse;
import com.fabrick.api.testbank.model.object.MoneyTransferResponse;
import com.fabrick.api.testbank.service.FabrickManager;
import com.fabrick.api.testbank.service.exception.ExceptionMapper;
import com.fabrick.api.testbank.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.Date;

@Controller
public class FabrickControllerImpl implements FabrickController {

    Logger logger = LoggerFactory.getLogger(FabrickControllerImpl.class);

    final
    FabrickManager fabrickManager;

    public FabrickControllerImpl(FabrickManager fabrickManager) {
        this.fabrickManager = fabrickManager;
    }

    @Override
    public ResponseEntity getAccountCash() {
        AccountCashResponse accountCashDTO = null;
        try{
            accountCashDTO = fabrickManager.getAccountCash();
        } catch (Exception e) {
            logger.error("Generic error retriving account information", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(accountCashDTO == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(accountCashDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity createPayment( CreditorDTO creditor) {
        MoneyTransferResponse moneyTransferResponse = null;
        try{
            moneyTransferResponse = fabrickManager.createPayment(creditor);
        } catch (ServiceException e) {
            logger.error("Generic error retriving account information", e);
            return new ResponseEntity<>(ExceptionMapper.map(e), e.getHttpStatus());
        } catch (Exception e) {
            logger.error("Generic error retriving account information", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(moneyTransferResponse == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(moneyTransferResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getTransactionsList(Date fromAccountingDate, Date toAccountingDate) {
        ListTransactionResponse listTransactionResponse = null;
        try{
            listTransactionResponse = fabrickManager.getTransactionsList(fromAccountingDate, toAccountingDate);
        } catch (ServiceException e) {
            logger.error("Generic error retriving account information", e);
            return new ResponseEntity<>(ExceptionMapper.map(e), e.getHttpStatus());
        } catch (Exception e) {
            logger.error("Generic error retriving account information", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(listTransactionResponse == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(listTransactionResponse, HttpStatus.OK);
    }
}
