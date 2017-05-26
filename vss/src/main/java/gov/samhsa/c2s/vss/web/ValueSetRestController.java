package gov.samhsa.c2s.vss.web;

import gov.samhsa.c2s.vss.service.ValueSetService;
import gov.samhsa.c2s.vss.service.dto.ValueSetDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/valuesets")
public class ValueSetRestController {

    private final ValueSetService valueSetService;

    public ValueSetRestController(ValueSetService valueSetService) {
        this.valueSetService = valueSetService;
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ValueSetDto getValueSetByCode(@PathVariable Long id){
        return valueSetService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCodeSystem(@Valid @RequestBody ValueSetDto valueSetDto){
        valueSetService.createValueSet(valueSetDto);
    }
}