package com.fabrick.api.testbank.controller;

import com.fabrick.api.testbank.model.dto.CreditorDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("napolil/bank")
public interface FabrickController {

        @RequestMapping(value = "/account-cash", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        ResponseEntity getAccountCash();

        @RequestMapping(value = "/money-transfers", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
        ResponseEntity createPayment(
                 @RequestBody CreditorDTO creditor);

        @RequestMapping(value = "/transactions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        ResponseEntity getTransactionsList(
                @RequestParam("fromAccountingDate")
                @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromAccountingDate,
                @RequestParam("toAccountingDate")
                @DateTimeFormat(pattern="yyyy-MM-dd") Date toAccountingDate);
}
