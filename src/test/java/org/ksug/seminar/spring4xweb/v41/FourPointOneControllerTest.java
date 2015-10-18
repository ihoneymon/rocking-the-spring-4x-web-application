package org.ksug.seminar.spring4xweb.v41;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ksug.seminar.spring4xweb.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class FourPointOneControllerTest {

    @Autowired
    private RestRepository repository;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).alwaysDo(print())
                .alwaysExpect(handler().handlerType(FourPointOneController.class)).build();
    }

    /**
     * {@link MvcUriComponentsBuilder}, {@link ResponseEntity} builder-style 예제
     * 
     * @throws Exception
     */
    @Test
    public void testCreateMethod() throws Exception {
        mockMvc.perform(post("/v41/create")).andExpect(handler().methodName("create"))
                .andExpect(status().is(HttpStatus.CREATED.value())).andExpect(status().is2xxSuccessful());
    }

    /**
     * {@link RequestMapping}에 {@link Optional}이 적용된 예제
     * 
     * @throws Exception
     */
    @Test
    public void testOptionalRequestMapping() throws Exception {
        String someDate = "2014-04-16";

        mockMvc.perform(get("/v41/optional").param("some-date", someDate))
                .andExpect(handler().methodName("optionalRequestMapping")).andExpect(status().is2xxSuccessful())
                .andExpect(content().string("some-date: " + someDate));
    }

    @Test
    public void test메서드에선언된ModelAttribute() throws Exception {
        // given
        Rest saveRest = repository.save(new Rest("modelAttribute: method"));

        mockMvc.perform(get("/v41/" + saveRest.getId())).andExpect(status().is2xxSuccessful());
    }
}
