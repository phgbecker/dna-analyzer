package br.com.meli.dna.analyzer.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DnaAnalyzerAPITest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void givenRequestWhenAnalyzeShouldReturnSimian() throws Exception {
        mockMvc.perform(post("/dna/simian")
                .header("Content-Type", APPLICATION_JSON_VALUE)
                .content("{ \"dna\": [ \"CTGATA\", \"TGACAC\", \"GAGTGA\", \"AGCGGT\", \"GAATGA\", \"ATGACC\" ] }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.is_simian", is(true)));
    }

    @Test
    void givenRequestWhenAnalyzeShouldReturnHuman() throws Exception {
        mockMvc.perform(post("/dna/simian")
                .header("Content-Type", APPLICATION_JSON_VALUE)
                .content("{ \"dna\": [ \"CTGAGA\", \"CTATGC\", \"TATTAT\", \"AGAGGG\", \"AGCCTA\", \"TCACTG\" ] }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.is_simian", is(false)));
    }

    @Test
    void givenInvalidRequestWhenAnalyzeShouldReturnAsExpected() throws Exception {
        mockMvc.perform(post("/dna/simian")
                .header("Content-Type", APPLICATION_JSON_VALUE)
                .content("{ \"dna\": null }"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("error",
                        is("Oops, the DNA analyses has failed. Details: The given sequence is invalid. Possible values are: [A, C, G, T]"))
                );
    }

    @Test
    void givenRequestWhenGetStatsShouldReturnAsExpected() throws Exception {
        mockMvc.perform(get("/dna/stats"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count_human_dna", is(1)))
                .andExpect(jsonPath("$.count_simian_dna", is(1)))
                .andExpect(jsonPath("$.ratio", is(1.0)));
    }
}