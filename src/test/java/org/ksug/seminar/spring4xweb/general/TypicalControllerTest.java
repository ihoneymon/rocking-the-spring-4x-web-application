package org.ksug.seminar.spring4xweb.general;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ksug.seminar.spring4xweb.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class TypicalControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).alwaysDo(print()).build();
    }

    @Test
    public void testDataBinder() throws Exception {
        // given
        WebDataBinder dataBinder = new WebDataBinder(null);

        // when
        dataBinder.registerCustomEditor(Level.class, new LevelPropertyEditor());

        // then
        assertThat(dataBinder.convertIfNecessary("1", Level.class), is(Level.BRONZE));
    }

    @Test
    public void testHelloWorldMethod() throws Exception {
        mockMvc.perform(get("/")).andExpect(handler().handlerType(TypicalController.class))
                .andExpect(handler().methodName("helloWorld"))
                .andExpect(content().string("Hello, Spring 4.x web application"));
    }

    @Test
    public void testOccurIllegalStateException() throws Exception {
        mockMvc.perform(get("/occur-exception")).andExpect(handler().handlerType(TypicalController.class))
                .andExpect(handler().methodName("occurIllegalStateException")).andExpect(status().isOk())
                .andExpect(content().string("IllegalStateException handle!!"));
    }

    @Test
    public void testHandleExceptionByController() throws Exception {
        mockMvc.perform(get("/handle-global-exception")).andExpect(handler().handlerType(TypicalController.class))
                .andExpect(handler().methodName("handleGlobalException")).andExpect(model().attributeExists("url"))
                .andExpect(model().attributeExists("message"));
        ;
    }
}
