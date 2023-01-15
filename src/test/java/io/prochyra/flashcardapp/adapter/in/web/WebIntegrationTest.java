package io.prochyra.flashcardapp.adapter.in.web;

import io.prochyra.flashcardapp.application.AddCardService;
import io.prochyra.flashcardapp.application.port.CardRepository;
import io.prochyra.flashcardapp.domain.Card;
import io.prochyra.flashcardapp.domain.StudySession;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@Tag("integration")
class WebIntegrationTest {

    public static final Card ANY_CARD = new Card("any concept", "any definition");

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CardRepository cardRepository;

    @MockBean
    AddCardService addCardService;

    @MockBean
    StudySession studySession;

    @Test
    void getOfHomepageReturns200AndStartView() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("start"));
    }

    @Test
    void postToCreateRedirectsToCreate() throws Exception {
        mockMvc.perform(post("/create"))
                .andExpect(redirectedUrl("/create"));
    }

    @Test
    void getOfCreateReturns200AndCreateView() throws Exception {
        mockMvc.perform(get("/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("card-creator"));
    }

    @Test
    void getOfFlashcardReturns200() throws Exception {
        given(studySession.currentCard())
                .willReturn(ANY_CARD);
        mockMvc.perform(get("/flashcard"))
                .andExpect(status().isOk());
    }

    @Test
    void postToStartRedirects() throws Exception {
        mockMvc.perform(post("/start"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void postToFlipRedirects() throws Exception {
        given(studySession.currentCard())
                .willReturn(ANY_CARD);
        mockMvc.perform(post("/flip"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void postToConfidenceRedirects() throws Exception {
        mockMvc.perform(post("/confidence"))
                .andExpect(status().is3xxRedirection());
    }
}
