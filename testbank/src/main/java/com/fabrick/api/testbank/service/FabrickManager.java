package com.fabrick.api.testbank.service;

import com.fabrick.api.testbank.client.FabrickManagerClient;
import com.fabrick.api.testbank.model.dto.AccountCashDTO;
import com.fabrick.api.testbank.model.dto.CreditorDTO;
import com.fabrick.api.testbank.model.object.AccountCashResponse;
import com.fabrick.api.testbank.model.object.FabrickResponse;
import com.fabrick.api.testbank.model.object.ListTransactionResponse;
import com.fabrick.api.testbank.model.object.MoneyTransferResponse;
import com.fabrick.api.testbank.service.exception.ServiceException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FabrickManager {

    final
    FabrickManagerClient fabrickManagerClient;


    public FabrickManager(FabrickManagerClient fabrickManagerClient) {
        this.fabrickManagerClient = fabrickManagerClient;
    }

    public AccountCashResponse getAccountCash() throws Exception {
        AccountCashResponse accountCash = fabrickManagerClient.getAccountCash();
        return accountCash;
    }

    public MoneyTransferResponse createPayment(CreditorDTO creditor) throws Exception {
        MoneyTransferResponse moneyTransferResponse = fabrickManagerClient.createPayment(creditor);
        return moneyTransferResponse;
    }

    public ListTransactionResponse getTransactionsList(Date fromAccountingDate, Date toAccountingDate) throws Exception {
        ListTransactionResponse moneyTransferResponse = fabrickManagerClient.getTransactionsList(fromAccountingDate, toAccountingDate);
        return moneyTransferResponse;
    }
}
