package org.ksug.seminar.spring4xweb.v42;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

@RestController
@RequestMapping("/v42")
public class ResponseBodyEmitterController {

    @Autowired
    private HttpStreamingService httpStreamingService;

    @RequestMapping("/events")
    public ResponseBodyEmitter handler() throws IOException, InterruptedException {
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        httpStreamingService.handle(emitter);
        return emitter;
    }
}
