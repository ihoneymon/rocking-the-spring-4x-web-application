package org.ksug.seminar.spring4xweb.v42;

import org.ksug.seminar.spring4xweb.general.TypicalController.HelloWorld;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostJsonController {

    @PostJson("/v42/post-json")
    public ResponseEntity postJson() {
        return ResponseEntity.status(HttpStatus.CREATED).body(new HelloWorld("KSUG", "RequestMapping meta annotation"));
    }
}
