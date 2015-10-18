package org.ksug.seminar.spring4xweb.v41;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestMapping;
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
        mockMvc.perform(get("/v41//model-attribtue")).andExpect(status().is2xxSuccessful())
                .andExpect(model().attribute("rest", is(notNullValue())));
    }

    @Test
    public void test메서드에선언된ModelAttribute_파라미터가존재하는경우() throws Exception {
        mockMvc.perform(get("/v41//model-attribtue").param("input", "input")).andExpect(status().is2xxSuccessful())
                .andExpect(model().attribute("rest", is(notNullValue())))
                .andExpect(model().attribute("input", is(notNullValue())));
    }

    /**
     * 아무런설정도 하지 않은 {@link Rest} 를 {@link Valid}로 검증시 결함이 있을 경우
     * {@link HttpStatus#BAD_REQUEST} 처리
     * 
     * @throws Exception
     */
    @Test
    public void testModelAttribute를_메서드인자로_선언한_것에_대한검증확인() throws Exception {
        mockMvc.perform(get("/v41/model-attribute/method-argument")).andExpect(status().is2xxSuccessful());
    }

}
