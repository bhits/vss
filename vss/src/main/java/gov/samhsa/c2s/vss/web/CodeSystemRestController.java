package gov.samhsa.c2s.vss.web;

import gov.samhsa.c2s.vss.service.CodeSystemService;
import gov.samhsa.c2s.vss.service.dto.CodeSystemDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/codesystems")
public class CodeSystemRestController {

    private final CodeSystemService codeSystemService;


    public CodeSystemRestController(CodeSystemService codeSystemService) {
        this.codeSystemService = codeSystemService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCodeSystem(@Valid @RequestBody CodeSystemDto codeSystemDto){
        codeSystemService.createCodeSystem(codeSystemDto);
    }
}
