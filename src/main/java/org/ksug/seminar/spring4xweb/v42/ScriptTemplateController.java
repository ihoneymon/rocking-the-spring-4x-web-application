package org.ksug.seminar.spring4xweb.v42;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/v42")
public class ScriptTemplateController {

    @RequestMapping("/script-template")
    public ModelAndView viewScriptTemplate() {
        ModelAndView mav = new ModelAndView("static/templates/hello.html");
        mav.addObject("title", "KSUG").addObject("body",
                "Moden Java web application with Spring");
        return mav;
    }
}
