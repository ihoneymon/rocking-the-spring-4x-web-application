package org.ksug.seminar.spring4xweb.v41;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

@RestController
@RequestMapping("/rest")
public class BuilderStyleController {

    @Autowired
    private RestRepository repository;

    @RequestMapping("/ok")
    public ResponseEntity ok() {
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/create", method = POST)
    public ResponseEntity create() {
        Rest savedRest = repository.saveAndFlush(new Rest("builder-create"));

        /**
         * 흠... ㅡ_-);; 까다롭군.. 메서드를 그대로 호출하는 탓에 변환....
         */
        UriComponents uriComponents = MvcUriComponentsBuilder.fromMethodName(BuilderStyleController.class, "getRest",
                savedRest.getId()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @RequestMapping(value = "/{id}", method = GET)
    public ResponseEntity getRest(@PathVariable Long id) {
        return ResponseEntity.ok(repository.findOne(id));
    }
}
