package org.ksug.seminar.spring4xweb.v42;

import java.io.IOException;

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
                    emitter.send("Hello, KSUG.");
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
                    emitter.send("Hello, honeymon.");
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        sendToHoneymon.start();
        Thread.sleep(1000);
        emitter.complete();
    }
}
