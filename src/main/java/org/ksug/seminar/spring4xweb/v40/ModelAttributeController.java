package org.ksug.seminar.spring4xweb.v40;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v40")
public class ModelAttributeController {
    @ModelAttribute("foo")
    public Foo getFoo() {
        return new Foo("Foo");
    }

    @ModelAttribute("boo")
    public Boo getBoo(@ModelAttribute("foo") Foo foo) {
        return new Boo(foo);
    }

    
    @RequestMapping("/model-attribute")
    public String test() {
        return "static/templates/model-attribute.html";
    }

    @RequestMapping
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Foo implements Serializable {
        private static final long serialVersionUID = 6666852559439260004L;
        private String message;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Boo implements Serializable {
        private static final long serialVersionUID = -5743117055946089950L;
        private Foo foo;
    }
}
