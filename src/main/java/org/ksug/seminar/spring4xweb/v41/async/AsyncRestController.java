package org.ksug.seminar.spring4xweb.v41.async;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author honeymon
 *
 */
@Slf4j
@RestController
@RequestMapping("/v41")
public class AsyncRestController {

    @Autowired
    private AsyncService asyncService;

    @RequestMapping("/async/{id}")
    public ListenableFuture<Map<String, String>> async(@PathVariable String id)
            throws InterruptedException {
        log.info("start");
        ListenableFuture<Map<String, String>> result = asyncService.execute(id);
        log.info("end");
        return result;
    }
}
