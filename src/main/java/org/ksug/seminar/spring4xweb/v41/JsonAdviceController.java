package org.ksug.seminar.spring4xweb.v41;

import org.ksug.seminar.spring4xweb.general.TypicalController.HelloWorld;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/v41")
public class JsonAdviceController {

    @RequestMapping("/jsonp")
    public @ResponseBody HelloWorld jsonp() {
        return new HelloWorld("KSUG", "Hello");
    }

    @RequestMapping("/jsonp-view")
    public String jsonView() {
        return "static/templates/jsonp-view.html";
    }
}
