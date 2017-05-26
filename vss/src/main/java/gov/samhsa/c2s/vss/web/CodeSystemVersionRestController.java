package gov.samhsa.c2s.vss.web;

import gov.samhsa.c2s.vss.service.CodeSystemVersionService;
import gov.samhsa.c2s.vss.service.dto.CodeSystemDto;
import gov.samhsa.c2s.vss.service.dto.CodeSystemVersionDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/codesystemversions")
public class CodeSystemVersionRestController {

    private final CodeSystemVersionService codeSystemVersionService;

    public CodeSystemVersionRestController(CodeSystemVersionService codeSystemVersionService) {
        this.codeSystemVersionService = codeSystemVersionService;
    }


    /**
     * @param codeSystemVersionDto
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCodeSystemVersion(@Valid @RequestBody CodeSystemVersionDto codeSystemVersionDto){
        codeSystemVersionService.createCodeSystemVersion(codeSystemVersionDto);
    }
}
