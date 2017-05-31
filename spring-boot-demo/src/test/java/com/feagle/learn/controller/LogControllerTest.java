package com.feagle.learn.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Created by feagle on 2017/5/25.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LogControllerTest {
    private final static Logger logger = LoggerFactory.getLogger(LogControllerTest.class);

    @Autowired
    private MockMvc mockMvc;
    @Test
    public void testLog() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/log")).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
