package org.ksug.seminar.spring4xweb.v40;

import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ksug.seminar.spring4xweb.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class FourPointZeroTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).alwaysDo(print()).build();
    }

    @Test
    public void testErrorDefault() throws Exception {
        mockMvc.perform(post("/error/default").contentType(MediaType.APPLICATION_JSON)).andExpect(view().name(is("static/templates/error/default.html")));
    }

    @Test
    public void testError400() throws Exception {
        mockMvc.perform(post("/error/400").contentType(MediaType.APPLICATION_JSON)).andExpect(view().name(is("static/templates/error/400.html")));
    }

    @Test
    public void testError404() throws Exception {
        mockMvc.perform(post("/error/404").contentType(MediaType.APPLICATION_JSON)).andExpect(view().name(is("static/templates/error/404.html")));
    }
}
