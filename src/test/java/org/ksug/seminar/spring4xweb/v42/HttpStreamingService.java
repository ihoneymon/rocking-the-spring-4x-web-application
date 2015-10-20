package org.ksug.seminar.spring4xweb.v42;

import java.io.IOException;

import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

public interface HttpStreamingService {

    void handle(ResponseBodyEmitter emitter) throws IOException, InterruptedException;
}
