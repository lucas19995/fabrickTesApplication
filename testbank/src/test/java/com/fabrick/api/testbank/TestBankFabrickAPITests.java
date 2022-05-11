package com.fabrick.api.testbank;

import com.fabrick.api.testbank.model.dto.CreditorDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class TestBankFabrickAPITests extends TestbankApplicationTests {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ObjectMapper mapper;

    private MockMvc mockMvc;
    private Date now;
    private SimpleDateFormat format;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testGetAccountCash() throws Exception {
        String url = "/napolil/bank/account-cash";

        this.mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("OK"));
    }

    @Test
    public void testCreatePayments() throws Exception {
        String url = "/napolil/bank/money-transfers";

        CreditorDTO creditorDTO = new CreditorDTO();
        creditorDTO.setName("Luca");
        creditorDTO.setCurrency("EUR");
        creditorDTO.setAmount(200);
        creditorDTO.setDescription("Test payment");
        this.mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(creditorDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.response.code").value("API000"));
    }

    @Test
    public void testGetTransactionsList() throws Exception {
        String url = "/napolil/bank/transactions";

        this.mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON)
                        .queryParam("fromAccountingDate", "2019-04-01")
                        .queryParam("toAccountingDate", "2019-04-01"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.payload.list").isEmpty());
    }
}
