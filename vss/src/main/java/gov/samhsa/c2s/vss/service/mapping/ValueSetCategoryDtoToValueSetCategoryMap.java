package gov.samhsa.c2s.vss.service.mapping;

import gov.samhsa.c2s.vss.domain.ValueSetCategory;
import gov.samhsa.c2s.vss.domain.ValueSetCategorySystem;
import gov.samhsa.c2s.vss.domain.ValueSetCategorySystemRepository;
import gov.samhsa.c2s.vss.service.dto.ValueSetCategoryDto;
import org.modelmapper.AbstractConverter;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValueSetCategoryDtoToValueSetCategoryMap extends PropertyMap<ValueSetCategoryDto, ValueSetCategory> {

    private final ValueSetCategorySystemConverter valueSetCategorySystemConverter;

    public ValueSetCategoryDtoToValueSetCategoryMap(ValueSetCategorySystemConverter valueSetCategorySystemConverter) {
        this.valueSetCategorySystemConverter = valueSetCategorySystemConverter;
    }

    @Override
    protected void configure() {
        map().getCodeName().setCode(source.getCode());
        map().getCodeName().setName(source.getName());
        using(valueSetCategorySystemConverter).map(source).setValueSetCategorySystem(null);
    }

    @Component
    private static class ValueSetCategorySystemConverter extends AbstractConverter<ValueSetCategoryDto,
           ValueSetCategorySystem> {
        private final ValueSetCategorySystemRepository valueSetCategorySystemRepository;

        private ValueSetCategorySystemConverter(ValueSetCategorySystemRepository valueSetCategorySystemRepository) {
            this.valueSetCategorySystemRepository = valueSetCategorySystemRepository;
        }

        @Override
        protected ValueSetCategorySystem convert(ValueSetCategoryDto source) {
            return valueSetCategorySystemRepository.findBySystem(source.getSystem()).get();
        }
    }

}
