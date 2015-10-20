package org.ksug.seminar.spring4xweb.v42;

import java.io.IOException;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

@Service
public class HttpStreamingServiceImpl implements HttpStreamingService {

    @Override
    public void handle(ResponseBodyEmitter emitter) throws IOException, InterruptedException {
        Thread sendToKSUG = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    emitter.send(new Date());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        sendToKSUG.start();

        Thread sendToHoneymon = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    emitter.send(new Message("Hello, KSUG"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        sendToHoneymon.start();
        Thread.sleep(1000);
        emitter.complete();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Message {
        private String content;
    }
}
