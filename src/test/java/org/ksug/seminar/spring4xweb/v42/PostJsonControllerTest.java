package org.ksug.seminar.spring4xweb.v42;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ksug.seminar.spring4xweb.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class PostJsonControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).alwaysDo(print()).build();
    }

    /**
     * 상위에 선언되어 있는 {@link RequestMapping}의 값을 상속받지 않는다. 따로논다.
     * 
     * @throws Exception
     */
    @Test
    public void testPostJsonRequestMappingMetaAnnotationUse() throws Exception {
        mockMvc.perform(
                post("/v42/post-json").accept(MediaType.APPLICATION_JSON_VALUE).contentType(
                        MediaType.APPLICATION_JSON_VALUE)).andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.name", is("KSUG")));
    }
}
