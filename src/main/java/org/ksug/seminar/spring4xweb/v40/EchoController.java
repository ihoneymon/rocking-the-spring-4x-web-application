package org.ksug.seminar.spring4xweb.v40;

import org.ksug.seminar.spring4xweb.general.TypicalController.HelloWorld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.config.AbstractMessageBrokerConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
        return new HelloWorld(hello.getName(), "Hello, ");
    }

    @RequestMapping("/echo")
    public String echoView() {
        return "static/templates/echo.html";
    }
}
