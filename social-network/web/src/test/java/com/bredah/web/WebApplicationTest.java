package com.bredah.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@WebAppConfiguration
class WebApplicationTest {

    @Autowired
    private WebApplication webApplication;


    private MockMvc mockMvc;

  
}
