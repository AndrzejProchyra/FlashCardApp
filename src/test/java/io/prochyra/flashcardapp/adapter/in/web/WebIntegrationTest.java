package io.prochyra.flashcardapp.adapter.in.web;

import io.prochyra.flashcardapp.application.CardService;
import io.prochyra.flashcardapp.application.port.CardRepository;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@Tag("integration")
class WebIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CardRepository cardRepository;

    @MockBean
    CardService cardService;

    @Test
    void getOfHomepageReturns200() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    void postToHomepageRedirectsToSamePage() throws Exception {
        mockMvc.perform(post("/"))
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void getOfFlashcardReturns200() throws Exception {
        mockMvc.perform(get("/flashcard"))
                .andExpect(status().isOk());
    }

    @Test
    void getStartPageReturns200() throws Exception {
        mockMvc.perform(get("/start"))
                .andExpect(status().isOk());
    }

    @Test
    void postToStartRedirects() throws Exception {
        mockMvc.perform(post("/start"))
                .andExpect(status().is3xxRedirection());
    }
}
