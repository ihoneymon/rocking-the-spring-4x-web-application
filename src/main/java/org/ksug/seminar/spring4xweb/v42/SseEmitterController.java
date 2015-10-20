package org.ksug.seminar.spring4xweb.v42;

import static org.springframework.web.servlet.mvc.method.annotation.SseEmitter.*;

import java.io.IOException;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Controller
@RequestMapping("/v42")
public class SseEmitterController {

    @RequestMapping("/sse-events")
    public SseEmitter handle() throws IOException {
        SseEmitter sseEmitter = new SseEmitter();
        sseEmitter.send(event().name("message").data(new Message("Hello, KSUG")));
        sseEmitter.send(event().name("greeting").data(new Greeting("Hello, Honeymon")));
        sseEmitter.send(event().name("date").data(new Date()));
        sseEmitter.complete();
        return sseEmitter;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Message {
        private String content;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Greeting {
        private String content;
    }
}
