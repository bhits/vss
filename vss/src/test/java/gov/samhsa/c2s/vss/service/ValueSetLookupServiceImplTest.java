package gov.samhsa.c2s.vss.service;

import gov.samhsa.c2s.vss.domain.ValueSetCategory;
import gov.samhsa.c2s.vss.domain.ValueSetCategoryRepository;
import gov.samhsa.c2s.vss.domain.valueobject.CodeName;
import gov.samhsa.c2s.vss.service.dto.ValueSetCategoryDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ValueSetLookupServiceImplTest {

    @Mock
    private ValueSetCategoryRepository valueSetCategoryRepository;

    @InjectMocks
    private ValueSetLookupServiceImpl sut;

    @Test
    public void testGetValueSetCategories() throws Exception {
        // Arrange
        final String vscMockCode = "vscMockCode";
        final String vscMockName = "vscMockName";
        final CodeName vscMockCodeName = CodeName.builder().code(vscMockCode).name(vscMockName).build();
        final String vscMockDescription = "vscMockDescription";
        final boolean vscMockIsFederal = true;
        final int vscMockDisplayOrder = 1;
        final String vscMock2Code = "vscMock2Code";
        final String vscMock2Name = "vscMock2Name";
        final CodeName vscMock2CodeName = CodeName.builder().code(vscMock2Code).name(vscMock2Name).build();
        final String vscMock2Description = "vscMock2Description";
        final boolean vscMockIsFedera2 = true;
        final int vscMock2DisplayOrder = 2;
        ValueSetCategory vscMock = mock(ValueSetCategory.class);
        ValueSetCategory vscMock2 = mock(ValueSetCategory.class);

        when(vscMock.getCodeName()).thenReturn(vscMockCodeName);
        when(vscMock.getDescription()).thenReturn(vscMockDescription);
        when(vscMock.isFederal()).thenReturn(vscMockIsFederal);
        when(vscMock.getDisplayOrder()).thenReturn(vscMockDisplayOrder);

        when(vscMock2.getCodeName()).thenReturn(vscMock2CodeName);
        when(vscMock2.getDescription()).thenReturn(vscMock2Description);
        when(vscMock2.isFederal()).thenReturn(vscMockIsFedera2);
        when(vscMock2.getDisplayOrder()).thenReturn(vscMock2DisplayOrder);

        List<ValueSetCategory> valueSetCategoryListMock = new ArrayList<>();
        valueSetCategoryListMock.add(vscMock);
        valueSetCategoryListMock.add(vscMock2);
        when(valueSetCategoryRepository.findAll()).thenReturn(valueSetCategoryListMock);

        List<ValueSetCategoryDto> valueSetCategoryDtoList = valueSetCategoryListMock.stream()
                .map(valueSetCategory -> new ValueSetCategoryDto(valueSetCategory.getCodeName().getCode(),
                        valueSetCategory.getCodeName().getName(),
                        valueSetCategory.getDescription(),
                        valueSetCategory.isFederal(),
                        valueSetCategory.getDisplayOrder()))
                .collect(toList());

        // Act
        List<ValueSetCategoryDto> valueSetCategoryDtos = sut.getValueSetCategories();

        // Assert
        assertEquals(valueSetCategoryDtoList, valueSetCategoryDtos);
        assertEquals(2, valueSetCategoryDtos.size());
        assertEquals(vscMockCode, valueSetCategoryDtos.get(0).getCode());
        assertEquals(vscMockName, valueSetCategoryDtos.get(0).getDisplayName());
        assertEquals(vscMockDescription, valueSetCategoryDtos.get(0)
                .getDescription());
        assertEquals(vscMockIsFederal, valueSetCategoryDtos.get(0).isFederal());
        assertEquals(vscMockDisplayOrder, valueSetCategoryDtos.get(0).getDisplayOrder());
        assertEquals(vscMock2Code, valueSetCategoryDtos.get(1).getCode());
        assertEquals(vscMock2Name, valueSetCategoryDtos.get(1).getDisplayName());
        assertEquals(vscMock2Description, valueSetCategoryDtos.get(1)
                .getDescription());
        assertEquals(vscMock2DisplayOrder, valueSetCategoryDtos.get(1).getDisplayOrder());
        verify(valueSetCategoryRepository, times(1)).findAll();
    }
}