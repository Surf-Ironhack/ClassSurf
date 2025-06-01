package com.Surf.class_surf.ClassesTest;

import com.Surf.class_surf.models.Classes;
import com.Surf.class_surf.repository.ClassesRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ClassesTestController {

    @Autowired
    private WebApplicationContext webApplicationContext;


    @Autowired
    private ClassesRepository classesRepository;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        objectMapper.findAndRegisterModules();
    }

    @Test
    void postCreatedClass() throws Exception {
        Classes classes = new Classes();
        classes.setNameClass("Dirección de las olas");
        classes.setLevel("Nivel 2");
        classes.setDate(LocalDate.of(2025, 6, 15));

        String body = objectMapper.writeValueAsString(classes);
        MvcResult mvcResult = mockMvc.perform(post("/classes/register")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(status().isCreated()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Nivel 2"));
    }

    @Test
    void getClassesId() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/classes/2"))
                .andExpect(status().isFound())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("De 0 a 1"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Remada"));
    }

    @Test
    public void patchChangeDate() throws Exception {
        LocalDate newDate = LocalDate.of(2025, 5, 31);
        Classes classes = new Classes();
        classes.setDate(newDate);

        mockMvc.perform(patch("/classes/updateDate/2")
                        .content(objectMapper.writeValueAsString(classes))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.date").value(newDate.toString()));
    }

    @Test
    void postAndDeleteClass() throws Exception {
        Classes classes = new Classes();
        classes.setNameClass("Olas");
        classes.setLevel("Nivel 2");
        classes.setDate(LocalDate.of(2025, 5, 28));

        String body = objectMapper.writeValueAsString(classes);
        MvcResult mvcResult = mockMvc.perform(post("/classes/register")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(status().isCreated()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("De 0 a 1"));

        mockMvc.perform(delete("/classes/delete/3"))
                .andExpect(status().isNoContent());
    }
}
