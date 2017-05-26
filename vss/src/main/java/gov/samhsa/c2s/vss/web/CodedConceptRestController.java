package gov.samhsa.c2s.vss.web;

import gov.samhsa.c2s.vss.service.CodedConceptService;
import gov.samhsa.c2s.vss.service.dto.CodedConceptDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/codedconcepts")
public class CodedConceptRestController {

    private final CodedConceptService codedConceptService;

    public CodedConceptRestController(CodedConceptService codedConceptService) {
        this.codedConceptService = codedConceptService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCodeSystem(@Valid @RequestBody CodedConceptDto codedConceptDto) {
        codedConceptService.createCodedConcept(codedConceptDto);
    }
}
