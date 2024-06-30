package com.eteration.simplebanking;

import com.eteration.simplebanking.controller.AccountController;
import com.eteration.simplebanking.dto.TransactionDTO;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.services.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ControllerTests {

    private MockMvc mockMvc;

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @Test
    public void testGetAccount() throws Exception {
        Account account = new Account("Kerem Karaca", "12345");
        when(accountService.findAccount(anyString())).thenReturn(account);

        mockMvc.perform(get("/account/12345"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"accountNumber\":\"12345\",\"owner\":\"Kerem Karaca\",\"balance\":0.0}"));
    }

    @Test
    public void testPostTransaction() throws Exception {
        Account account = new Account("Kerem Karaca", "12345");
        when(accountService.findAccount(anyString())).thenReturn(account);

        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setType("deposit");
        transactionDTO.setAmount(100.0);

        mockMvc.perform(post("/account/12345/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"type\":\"deposit\",\"amount\":100.0}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"status\":\"OK\"}"));
    }
}
