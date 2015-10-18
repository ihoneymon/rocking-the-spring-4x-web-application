package org.ksug.seminar.spring4xweb.v40;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class NewRestController {

    @RequestMapping
    public RestHelloWorld helloRestController() {
        return new RestHelloWorld("Hello", "KSUG");
    }

    @RequestMapping("/occur-exception")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity occurException() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RestHelloWorld {
        private String comment;
        private String target;

        public String getGreeting() {
            return String.format("%s, %s", this.comment, this.target);
        }
    }
}
