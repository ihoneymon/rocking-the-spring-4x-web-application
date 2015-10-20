package org.ksug.seminar.spring4xweb.v42;

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
        sseEmitter.send(new Message("Hello, KSUG"));
        sseEmitter.send(new Greeting("Hello, Honeymon"));
        sseEmitter.send(new Date());
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
