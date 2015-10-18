package org.ksug.seminar.spring4xweb.v41;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.Date;
import java.util.Optional;

import org.ksug.seminar.spring4xweb.general.TypicalController.HelloWorld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

/**
 * {@link ResponseEntity}, {@link RequestEntity} buider-style 적용
 * {@link RequestParam}에 대한 {@link Optional} 적용
 * @author honeymon
 *
 */
@RestController
@RequestMapping("/v41")
public class FourPointOneController {

    @Autowired
    private RestRepository repository;

    @RequestMapping("/ok")
    public ResponseEntity ok() {
        return ResponseEntity.ok().build();
    }
    
    @ModelAttribute("rest")
    public Rest rest() {
        return new Rest("v41");
    }

    @RequestMapping(value = "/create", method = POST)
    public ResponseEntity create() {
        Rest savedRest = repository.saveAndFlush(new Rest("builder-create"));

        UriComponents uriComponents = MvcUriComponentsBuilder.fromMethodName(FourPointOneController.class, "getRest",
                savedRest.getId()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @ModelAttribute
    @RequestMapping(value = "/{id}", method = GET)
    public ResponseEntity getRest(@PathVariable Long id) {
        return ResponseEntity.ok(repository.findOne(id));
    }

    @RequestMapping(value = "/optional", method = GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity optionalRequestMapping(@RequestParam(value = "some-date") Optional<String> someDate) {
        StringBuilder sb = new StringBuilder("some-date: ");
        someDate.ifPresent(value -> sb.append(value.toString()));
        return ResponseEntity.ok(sb.toString());
    }
}
