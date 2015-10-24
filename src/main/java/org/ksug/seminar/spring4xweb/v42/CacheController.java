package org.ksug.seminar.spring4xweb.v42;

import java.util.concurrent.TimeUnit;

import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v42")
public class CacheController {

    @RequestMapping("/cache-control")
    public ResponseEntity cacheController() {
        return ResponseEntity
                .ok()
                .cacheControl(
                        CacheControl.maxAge(0, TimeUnit.SECONDS).cachePublic()
                                .noTransform()).build();
    }

    @RequestMapping("/cache-control2")
    public ResponseEntity cacheController2() {
        return ResponseEntity
                .ok()
                .cacheControl(
                        CacheControl.maxAge(1, TimeUnit.SECONDS).cachePrivate())
                .build();
    }
}
