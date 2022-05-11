package com.fabrick.api.testbank.client;

import com.fabrick.api.testbank.model.dto.AccountCashDTO;
import com.fabrick.api.testbank.model.dto.CreditorDTO;
import com.fabrick.api.testbank.model.dto.MoneyTransferDTO;
import com.fabrick.api.testbank.model.object.AccountCashResponse;
import com.fabrick.api.testbank.model.object.FabrickResponse;
import com.fabrick.api.testbank.model.object.ListTransactionResponse;
import com.fabrick.api.testbank.model.object.MoneyTransferResponse;
import com.fabrick.api.testbank.service.exception.ServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class FabrickManagerClient {

    private static final Logger logger = LoggerFactory.getLogger(FabrickManagerClient.class);

    @Value("${local.bancasella.host}")
    private String bancasellaHost;

    @Value("${local.bancasella.nameapikey}")
    private String nameApiKey;

    @Value("${local.bancasella.namecontenttype}")
    private String nameContentType;

    @Value("${local.bancasella.nameauthschema}")
    private String nameAuthSchema;

    @Value("${local.bancasella.nametimezone}")
    private String nameTimeZone;

    @Value("${local.bancasella.contenttype}")
    private String contentType;

    @Value("${local.bancasella.authschema}")
    private String authSchema;

    @Value("${local.bancasella.timezone}")
    private String timeZone;

    @Value("${local.bancasella.apikey}")
    private String apiKey;

    @Value("${local.bancasella.endpointaccount}")
    private String endpointAccount;

    @Value("${local.bancasella.account}")
    private String accountId;

    @Value("${local.bancasella.endpointCreateMoneyTransfer}")
    private String endpointCreateMoneyTransfer;

    @Value("${local.bancasella.endpointTransactions}")
    private String endpointTransactions;

    final
    RestTemplate restTemplate;

    public FabrickManagerClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public AccountCashResponse getAccountCash() throws Exception {
        URL url = buildUrl(endpointAccount,null,accountId, null );
        HttpEntity<?> entity = initHttpEntity(null);

        ResponseEntity<AccountCashResponse> response = restTemplate.exchange(
                url.toURI(),
                HttpMethod.GET,
                entity,
                AccountCashResponse.class);

        return response.getBody();
    }

    public MoneyTransferResponse createPayment(CreditorDTO creditorDTO) throws Exception {
        URL url = buildUrl(endpointAccount,endpointCreateMoneyTransfer,accountId, null );
        HttpEntity<?> entity = initHttpEntity(creditorDTO);
        ResponseEntity<MoneyTransferResponse> response = null;

        try {
            response = restTemplate.exchange(
                    url.toURI(),
                    HttpMethod.POST,
                    entity,
                    MoneyTransferResponse.class);
        } catch(Exception e) {
            if (e instanceof HttpClientErrorException) {
                HttpClientErrorException exception = (HttpClientErrorException) e;
                ObjectMapper objectMapper = new ObjectMapper();
                MoneyTransferResponse moneyTransferResponse = objectMapper.readValue(exception.getResponseBodyAsString(),
                        MoneyTransferResponse.class);
                throw new ServiceException(exception,
                        moneyTransferResponse
                                .errors
                                .stream()
                                .map(errorResponse -> errorResponse.code)
                                .collect(Collectors.toList()), accountId);
            }
        }

        return response.getBody();
    }

    public ListTransactionResponse getTransactionsList(Date fromAccountingDate, Date toAccountingDate) throws Exception {
        String datePattern = "yyyy-MM-dd";
        SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("fromAccountingDate", dateFormatter.format(fromAccountingDate));
        params.put("toAccountingDate", dateFormatter.format(toAccountingDate));

        URL url = buildUrl(endpointAccount,endpointTransactions,accountId, params);
        HttpEntity<?> entity = initHttpEntity(null);
        ResponseEntity<ListTransactionResponse> response = null;

        try {
            response = restTemplate.exchange(
                    url.toURI(),
                    HttpMethod.GET,
                    entity,
                    ListTransactionResponse.class);
        } catch(Exception e) {
            if (e instanceof HttpClientErrorException) {
                HttpClientErrorException exception = (HttpClientErrorException) e;
                ObjectMapper objectMapper = new ObjectMapper();
                ListTransactionResponse moneyTransferResponse = objectMapper.readValue(exception.getResponseBodyAsString(),
                        ListTransactionResponse.class);
                throw new ServiceException(exception,
                        moneyTransferResponse
                                .errors
                                .stream()
                                .map(errorResponse -> errorResponse.code)
                                .collect(Collectors.toList()), accountId);
            }
        }

        return response.getBody();
    }

    private HttpEntity<?> initHttpEntity(CreditorDTO bodyRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(nameContentType,contentType);
        headers.set(nameAuthSchema, authSchema);
        headers.set(nameTimeZone, timeZone);
        headers.set(nameAuthSchema, authSchema);
        headers.set(nameApiKey, apiKey);
        HttpEntity<?> entity = new HttpEntity<>(bodyRequest,headers);
        return entity;
    }

    private URL buildUrl(String servicePath, String serviceAction, String accountId, Map<String, Object> paramenters) throws Exception {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .newInstance().host(bancasellaHost);

        uriComponentsBuilder = uriComponentsBuilder.scheme("https");

        if (servicePath != null && accountId != null) {
            uriComponentsBuilder = uriComponentsBuilder.path(servicePath);
            uriComponentsBuilder = uriComponentsBuilder.path(accountId);
        }

        if (serviceAction != null) {
            uriComponentsBuilder = uriComponentsBuilder.path(serviceAction);
        }

        if (paramenters != null && !paramenters.isEmpty()) {
            for (String key : paramenters.keySet()) {
                Object value = paramenters.get(key);
                uriComponentsBuilder = uriComponentsBuilder.queryParam(key,
                        value);
            }
        }

        try {
            return uriComponentsBuilder.build().toUri().toURL();
        } catch (MalformedURLException e) {
            throw new Exception(e.getMessage());
        }
    }
}
