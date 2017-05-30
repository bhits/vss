package gov.samhsa.c2s.vss.service.mapping;

import gov.samhsa.c2s.vss.service.dto.CodedConceptDto;
import gov.samhsa.c2s.vss.service.dto.CodedConceptUploadDto;
import org.modelmapper.AbstractConverter;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CodedConceptUploadDtoToCodedConceptDtoMap extends PropertyMap<CodedConceptUploadDto, CodedConceptDto> {
    private final ValueSetConverter valueSetConverter;

    public CodedConceptUploadDtoToCodedConceptDtoMap(ValueSetConverter valueSetConverter) {
        this.valueSetConverter = valueSetConverter;
    }

    @Override
    protected void configure() {
       using(valueSetConverter).map(source).setValueSetIds(null);
         skip().setId(null);
    }

    @Component
    private static class ValueSetConverter extends AbstractConverter<CodedConceptUploadDto, List<Long>> {

        @Override
        protected List<Long> convert(CodedConceptUploadDto source) {
            String[] values = source.getUploadValueSets().split(",");
           List<String> ids = Arrays.asList(values);
           return ids.stream().map(id -> Long.parseLong(id)).collect(Collectors.toList());
        }
    }

}
