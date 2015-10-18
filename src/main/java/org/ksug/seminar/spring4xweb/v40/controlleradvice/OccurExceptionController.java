package org.ksug.seminar.spring4xweb.v40.controlleradvice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v40")
public class OccurExceptionController {

    @RequestMapping("/occur-exception/package")
    public String occurExceptionOnPackage() throws Exception {
        throw new Exception(OccurExceptionController.class.getPackage().getName());
    }
}
