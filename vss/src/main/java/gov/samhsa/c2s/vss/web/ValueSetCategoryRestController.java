package gov.samhsa.c2s.vss.web;

import gov.samhsa.c2s.vss.domain.ValueSetCategory;
import gov.samhsa.c2s.vss.service.ValueSetCategoryService;
import gov.samhsa.c2s.vss.service.dto.CodeSystemDto;
import gov.samhsa.c2s.vss.service.dto.ValueSetCategoryDto;
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
@RequestMapping("/valuesetcategories")
public class ValueSetCategoryRestController {

    private final ValueSetCategoryService valueSetCategoryService;

    public ValueSetCategoryRestController(ValueSetCategoryService valueSetCategoryService) {
        this.valueSetCategoryService = valueSetCategoryService;
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public ValueSetCategoryDto searchByCode(@RequestParam("code") String code){
        return valueSetCategoryService.findByCode(code);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ValueSetCategoryDto getValueSetCategoryByCode(@PathVariable Long id){
        return valueSetCategoryService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCodeSystem(@Valid @RequestBody ValueSetCategoryDto valueSetCategoryDto){
        valueSetCategoryService.createValueSetCategory(valueSetCategoryDto);
    }
}