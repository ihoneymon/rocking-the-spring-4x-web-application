package org.ksug.seminar.spring4xweb.v40;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ksug.seminar.spring4xweb.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class NewRestControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).alwaysDo(print())
                .build();
    }

    @Test
    public void testHelloRestController() throws Exception {
        mockMvc.perform(get("/rest"))
                .andExpect(handler().handlerType(NewRestController.class))
                .andExpect(handler().methodName("helloRestController"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.comment", is("Hello")))
                .andExpect(jsonPath("$.target", is("KSUG")))
                .andExpect(
                        header().string("KSUG",
                                is("Modern Java web application with Spring")));
    }

    /**
     * {@link RestController}에서 발생한 {@link RestControllerExceptionHandler}에서
     * {@link HttpStatus#INTERNAL_SERVER_ERROR}로 처리하도록 함
     * 
     * @throws Exception
     */
    @Test
    public void testOccurException() throws Exception {
        mockMvc.perform(get("/rest/occur-exception"))
                .andExpect(handler().handlerType(NewRestController.class))
                .andExpect(handler().methodName("occurException"))
                .andExpect(
                        status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .andExpect(jsonPath("$.url", is("/rest/occur-exception")))
                .andExpect(jsonPath("$.status", is("INTERNAL_SERVER_ERROR")));
    }
}
