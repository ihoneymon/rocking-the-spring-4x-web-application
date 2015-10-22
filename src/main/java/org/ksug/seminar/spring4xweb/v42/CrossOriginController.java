package org.ksug.seminar.spring4xweb.v42;

import org.ksug.seminar.spring4xweb.general.TypicalController.HelloWorld;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller에서 하는 {@link CrossOrigin} 설정
 * 
 * @author honeymon
 *
 */
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/v42")
public class CrossOriginController {

    @CrossOrigin("http://ksug.org")
    @RequestMapping("/corss-origin")
    public HelloWorld crossOrigin(HelloWorld hello) {
        return hello;
    }
}
