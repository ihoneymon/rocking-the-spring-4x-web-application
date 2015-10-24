package org.ksug.seminar.spring4xweb.v42;

import java.io.IOException;

import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

public interface HttpStreamingService {

    /**
     * ResponseBodyEmitter는 다양한 객체를 비동기식으로 다룰 수 있다.
     * 
     * @param emitter
     *            {@link ResponseBodyEmitter}
     * @throws IOException
     * @throws InterruptedException
     */
    void handle(ResponseBodyEmitter emitter) throws IOException,
            InterruptedException;
}
