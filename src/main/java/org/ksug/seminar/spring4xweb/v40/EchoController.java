package org.ksug.seminar.spring4xweb.v40;

import org.ksug.seminar.spring4xweb.general.TypicalController.HelloWorld;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class EchoController {

    @MessageMapping("/echo")
    @SendTo("/subscribe/echo")
    public HelloWorld helloWorld(HelloWorld hello) {
        return new HelloWorld("Receive: " + hello.getName(), "Receive: " + hello.getComment());
    }
}
