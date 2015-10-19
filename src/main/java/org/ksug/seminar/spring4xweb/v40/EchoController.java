package org.ksug.seminar.spring4xweb.v40;

import org.ksug.seminar.spring4xweb.general.TypicalController.HelloWorld;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class EchoController {

    /**
     * WebSocket 에서 사용하던 MessageHandler를 구현하지 않아도 사용할 수 있어 웹 MVC 처럼 개발 가능
     * 
     * @param hello
     * @return
     */
    @MessageMapping("/stomp/echo")
    @SendTo("/subscribe/echo")
    public HelloWorld helloWorld(HelloWorld hello) {
        return new HelloWorld("Receive: " + hello.getName(), "Receive: " + hello.getComment());
    }
}
