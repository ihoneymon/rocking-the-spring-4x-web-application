package org.ksug.seminar.spring4xweb.general;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.ksug.seminar.spring4xweb.general.BeforeGlobalExceptionHandler.GlobalException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 일반적인 컨트롤러
 * 
 * @author honeymon
 *
 */
@Controller
public class TypicalController {

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(Level.class, new LevelPropertyEditor());
    }

    @ModelAttribute("helloModelAttribute")
    public HelloWorld helloWorld() {
        return new HelloWorld("Spring 4.x web application", "Hello");
    }

    @RequestMapping("/")
    public @ResponseBody String helloWorld(@ModelAttribute("helloModelAttribute") HelloWorld helloWorld) {
        return String.format("%s, %s", helloWorld.getComment(), helloWorld.getName());
    }

    @RequestMapping("/occur-exception")
    public String occurIllegalStateException() {
        throw new IllegalStateException("illegalStateException");
    }

    @RequestMapping("/handle-global-exception")
    public String handleGlobalException() throws GlobalException {
        throw new GlobalException("exception");
    }

    @ExceptionHandler(IllegalStateException.class)
    public @ResponseBody String handlerError(IllegalStateException e) {
        return "IllegalStateException handle!!";
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HelloWorld {
        private String name;
        private String comment;
    }
}
