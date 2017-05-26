package gov.samhsa.c2s.vss.service;

import gov.samhsa.c2s.vss.domain.*;
import gov.samhsa.c2s.vss.domain.valueobject.CodeName;
import gov.samhsa.c2s.vss.service.dto.CodedConceptAndCodeSystemOidDto;
import gov.samhsa.c2s.vss.service.dto.ValueSetCategoryDto;
import gov.samhsa.c2s.vss.service.dto.ValueSetCategoryMapDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ValueSetLookupServiceImplTest {

    @Mock
    private ValueSetCategoryRepository valueSetCategoryRepository;

    @Mock
    private ValueSetRepository valueSetRepository;

    @Mock
    private CodedConceptRepository codedConceptRepository;

    @Mock
    private CodeSystemVersionRepository codeSystemVersionRepository;

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
        final String system = "http://hl7.org/fhir/v3/ActCode";
        ValueSetCategory vscMock = mock(ValueSetCategory.class);
        ValueSetCategory vscMock2 = mock(ValueSetCategory.class);
        ValueSetCategorySystem systemMock = mock(ValueSetCategorySystem.class);
        when(systemMock.getSystem()).thenReturn(system);

        when(vscMock.getCodeName()).thenReturn(vscMockCodeName);
        when(vscMock.getDescription()).thenReturn(vscMockDescription);
        when(vscMock.isFederal()).thenReturn(vscMockIsFederal);
        when(vscMock.getDisplayOrder()).thenReturn(vscMockDisplayOrder);
        when(vscMock.getValueSetCategorySystem()).thenReturn(systemMock);

        when(vscMock2.getCodeName()).thenReturn(vscMock2CodeName);
        when(vscMock2.getDescription()).thenReturn(vscMock2Description);
        when(vscMock2.isFederal()).thenReturn(vscMockIsFedera2);
        when(vscMock2.getDisplayOrder()).thenReturn(vscMock2DisplayOrder);
        when(vscMock2.getValueSetCategorySystem()).thenReturn(systemMock);

        List<ValueSetCategory> valueSetCategoryListMock = new ArrayList<>();
        valueSetCategoryListMock.add(vscMock);
        valueSetCategoryListMock.add(vscMock2);
        when(valueSetCategoryRepository.findAll()).thenReturn(valueSetCategoryListMock);

        List<ValueSetCategoryDto> valueSetCategoryDtoList = valueSetCategoryListMock.stream()
                .map(valueSetCategory -> new ValueSetCategoryDto(
                        valueSetCategory.getId(), valueSetCategory.getCodeName().getCode(),
                        valueSetCategory.getCodeName().getName(),
                        valueSetCategory.getDescription(),
                        valueSetCategory.isFederal(),
                        valueSetCategory.getDisplayOrder(),
                        valueSetCategory.getValueSetCategorySystem().getSystem()))
                .collect(toList());

        // Act
        List<ValueSetCategoryDto> valueSetCategoryDtos = sut.getValueSetCategories();

        // Assert
        assertEquals(valueSetCategoryDtoList, valueSetCategoryDtos);
        assertEquals(2, valueSetCategoryDtos.size());
        assertEquals(vscMockCode, valueSetCategoryDtos.get(0).getCode());
        assertEquals(vscMockName, valueSetCategoryDtos.get(0).getName());
        assertEquals(vscMockDescription, valueSetCategoryDtos.get(0)
                .getDescription());
        assertEquals(vscMockIsFederal, valueSetCategoryDtos.get(0).isFederal());
        assertEquals(vscMockDisplayOrder, valueSetCategoryDtos.get(0).getDisplayOrder());
        assertEquals(vscMock2Code, valueSetCategoryDtos.get(1).getCode());
        assertEquals(vscMock2Name, valueSetCategoryDtos.get(1).getName());
        assertEquals(vscMock2Description, valueSetCategoryDtos.get(1)
                .getDescription());
        assertEquals(vscMock2DisplayOrder, valueSetCategoryDtos.get(1).getDisplayOrder());
        verify(valueSetCategoryRepository, times(1)).findAll();
    }

    @Test
    public void testLookupValueSetCategoryMaps() throws Exception {
        // Arrange
        final String mockCodeSystemOid = "mockCodeSystemOid";
        final String mockCodedConceptCode = "mockCodedConceptCode";
        final Long mockCSVId = 1L;
        final Long mockCCId = 1L;
        final Long mockVSId = 1L;
        final String mockVSCCode = "mockVSCCode";
        final String mockVSCName = "mockVSCName";
        final String mockCCCode = "mockCCCode";
        final String mockCCName = "mockCCName";
        //Request
        final CodedConceptAndCodeSystemOidDto mockRequestDto = mock(CodedConceptAndCodeSystemOidDto.class);
        when(mockRequestDto.getCodedConceptCode()).thenReturn(mockCodedConceptCode);
        when(mockRequestDto.getCodeSystemOid()).thenReturn(mockCodeSystemOid);
        List<CodedConceptAndCodeSystemOidDto> mockRequestCodeAndCSOidDtos = new ArrayList<>();
        mockRequestCodeAndCSOidDtos.add(mockRequestDto);
        //Response
        final CodeSystemVersion mockCSV = mock(CodeSystemVersion.class);
        when(mockCSV.getId()).thenReturn(mockCSVId);
        when(codeSystemVersionRepository.findTopByCodeSystemCodeSystemOidOrderByVersionOrderDesc(mockCodeSystemOid)).thenReturn(Optional.of(mockCSV));
        final CodeName mockCCCodeName = mock(CodeName.class);
        when(mockCCCodeName.getCode()).thenReturn(mockCCCode);
        when(mockCCCodeName.getName()).thenReturn(mockCCName);
        final CodedConcept mockCC = mock(CodedConcept.class);
        when(mockCC.getId()).thenReturn(mockCCId);
        when(mockCC.getCodeName()).thenReturn(mockCCCodeName);
        when(codedConceptRepository.findByCodeSystemVersionIdAndCodeNameCode(mockCSV.getId(), mockCodedConceptCode)).thenReturn(Optional.of(mockCC));
        final CodeName mockVSCCodeName = mock(CodeName.class);
        when(mockVSCCodeName.getCode()).thenReturn(mockVSCCode);
        when(mockVSCCodeName.getName()).thenReturn(mockVSCName);
        final ValueSetCategory mockVSC = mock(ValueSetCategory.class);
        when(mockVSC.getCodeName()).thenReturn(mockVSCCodeName);
        final ValueSet mockVS = mock(ValueSet.class);
        when(mockVS.getId()).thenReturn(mockVSId);
        when(mockVS.getValueSetCategory()).thenReturn(mockVSC);
        List<ValueSet> mockValueSets = new ArrayList<>();
        mockValueSets.add(mockVS);
        when(valueSetRepository.findAllByCodedConceptsId(mockCC.getId())).thenReturn(mockValueSets);
        Set<String> valueSetCategoryCodes = mockValueSets.stream()
                .map(ValueSet::getValueSetCategory)
                .map(ValueSetCategory::getCodeName)
                .map(CodeName::getCode)
                .collect(Collectors.toSet());
        final ValueSetCategoryMapDto mockVscMapDto = new ValueSetCategoryMapDto(mockCodedConceptCode, mockCodeSystemOid, valueSetCategoryCodes);
        List<ValueSetCategoryMapDto> mockResponseVSCMapDtos = new ArrayList<>();
        mockResponseVSCMapDtos.add(mockVscMapDto);

        // Act
        List<ValueSetCategoryMapDto> valueSetCategoryMapDtos = sut.lookupValueSetCategoryMaps(mockRequestCodeAndCSOidDtos);

        // Assert
        assertEquals(mockResponseVSCMapDtos, valueSetCategoryMapDtos);
        assertEquals(1, valueSetCategoryMapDtos.size());
        assertEquals(mockCodedConceptCode, valueSetCategoryMapDtos.get(0).getCodedConceptCode());
        assertEquals(mockCodeSystemOid, valueSetCategoryMapDtos.get(0).getCodeSystemOid());
        assertEquals(mockVSCCode, valueSetCategoryMapDtos.get(0).getValueSetCategoryCodes().iterator().next());
        verify(codeSystemVersionRepository, times(1)).findTopByCodeSystemCodeSystemOidOrderByVersionOrderDesc(mockCodeSystemOid);
        verify(codedConceptRepository, times(1)).findByCodeSystemVersionIdAndCodeNameCode(mockCSV.getId(), mockCodedConceptCode);
        verify(valueSetRepository, times(1)).findAllByCodedConceptsId(mockCC.getId());
    }
}