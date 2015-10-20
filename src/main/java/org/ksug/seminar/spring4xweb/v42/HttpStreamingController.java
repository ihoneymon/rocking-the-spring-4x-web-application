package org.ksug.seminar.spring4xweb.v42;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

@Controller
@RequestMapping("/v42")
public class HttpStreamingController {

    @Autowired
    private HttpStreamingService httpStreamingService;

    @RequestMapping("/events")
    public ResponseBodyEmitter handler() throws IOException, InterruptedException {
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        httpStreamingService.handle(emitter);
        return emitter;
    }
}
