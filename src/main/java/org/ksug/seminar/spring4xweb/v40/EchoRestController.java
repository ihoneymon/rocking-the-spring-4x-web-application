package org.ksug.seminar.spring4xweb.v40;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.config.AbstractMessageBrokerConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v40")
public class EchoRestController {

    /**
     * 
     * 애플리케이션에서는 'brokerChannel'를 통해서 메시지를 보낼 수 있다. 보다 쉬운 방법으로는
     * {@link SimpMessagingTemplate}를 주입받고, 그것을 이용해서 보내는 방법이 있다.
     * 
     * spring-messaging 패키지 내부에 {@link AbstractMessageBrokerConfiguration}에서 설정됨
     */
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @RequestMapping(value = "/boardcast-echo", method = RequestMethod.POST)
    public void broadcastEcho() {
        log.debug("template: {}", messagingTemplate);
        this.messagingTemplate.convertAndSend("/app/stomp/echo", "Hello, KSUG");
    }
}
