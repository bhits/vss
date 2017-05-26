package gov.samhsa.c2s.vss.service.mapping;

import gov.samhsa.c2s.vss.domain.ValueSet;
import gov.samhsa.c2s.vss.domain.ValueSetCategory;
import gov.samhsa.c2s.vss.domain.ValueSetCategoryRepository;
import gov.samhsa.c2s.vss.service.dto.ValueSetDto;
import org.modelmapper.AbstractConverter;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class ValueSetDtoToValueSetMap extends PropertyMap<ValueSetDto, ValueSet> {

    private final ValueSetCategoryConverter valueSetCategoryConverter;

    public ValueSetDtoToValueSetMap(ValueSetCategoryConverter valueSetCategoryConverter) {
        this.valueSetCategoryConverter = valueSetCategoryConverter;
    }


    @Override
    protected void configure() {
        map().getCodeName().setCode(source.getCode());
        map().getCodeName().setName(source.getName());
        using(valueSetCategoryConverter).map(source).setValueSetCategory(null);
    }

    @Component
    private static class ValueSetCategoryConverter extends AbstractConverter<ValueSetDto,
            ValueSetCategory> {
        private final ValueSetCategoryRepository valueSetCategoryRepository;

        private ValueSetCategoryConverter(ValueSetCategoryRepository valueSetCategoryRepository) {
            this.valueSetCategoryRepository = valueSetCategoryRepository;
        }


        @Override
        protected ValueSetCategory convert(ValueSetDto source) {
            return valueSetCategoryRepository.findByCodeName_Code(source.getValueSetCatCode()).get();
        }
    }

}
