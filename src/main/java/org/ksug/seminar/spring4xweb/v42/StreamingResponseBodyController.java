package org.ksug.seminar.spring4xweb.v42;

import java.nio.charset.Charset;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@Slf4j
@RestController
@RequestMapping("/v42")
public class StreamingResponseBodyController {

    @RequestMapping("/stream-events")
    public StreamingResponseBody handle() {
        return os -> {
            for (int i=1; i <= 10; i++) {
                String line = String.valueOf(i) + "\n";
                os.write(line.getBytes(Charset.forName("UTF-8")));
                os.flush();
                log.debug("Wrote value: " + i);
            }
        };
    }

    private static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        }
        catch (InterruptedException ex) {
            // ignore
        }
    }
}
