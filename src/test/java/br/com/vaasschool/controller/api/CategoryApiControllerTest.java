package br.com.vaasschool.controller.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CategoryApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnInJsonFormat() throws Exception {
        URI uri = new URI("/api/categories");

        mockMvc.perform(MockMvcRequestBuilders
                .get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));;
    }

    @Test
    public void shouldReturnInXMLFormat() throws Exception {
        URI uri = new URI("/api/categories");

        mockMvc.perform(MockMvcRequestBuilders
                .get(uri)
                .contentType(MediaType.TEXT_XML_VALUE))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));;
    }

    @Test
    public void shouldClearCache() throws Exception {
        URI uri = new URI("api/categories/bGltcGEtby1jYWNoZS1kYS1hcGktYWU");

    }
}
