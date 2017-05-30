package gov.samhsa.c2s.vss.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.samhsa.c2s.vss.service.ValueSetLookupService;
import gov.samhsa.c2s.vss.service.dto.CodedConceptAndCodeSystemOidDto;
import gov.samhsa.c2s.vss.service.dto.ValueSetCategoryDto;
import gov.samhsa.c2s.vss.service.dto.ValueSetCategoryMapDto;
import gov.samhsa.c2s.vss.service.exception.ValueSetCategoriesSearchFailedException;
import gov.samhsa.c2s.vss.service.exception.ValueSetCategoryMapsSearchFailedException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static gov.samhsa.c2s.common.unit.matcher.ArgumentMatchers.matching;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ValueSetLookupRestControllerTest {

    private MockMvc mvc;
    private ObjectMapper objectMapper;

    @Mock
    private ValueSetLookupService valueSetLookupService;

    @InjectMocks
    private ValueSetLookupRestController sut;

    @Before
    public void setUp() throws Exception {
        objectMapper = new ObjectMapper();
        mvc = MockMvcBuilders.standaloneSetup(this.sut).build();
    }

    @Test
    public void testGetValueSetCategories() throws Exception {
        // Arrange
        List<ValueSetCategoryDto> valueSetCategoryDtoList = new ArrayList<>();
        final Long id =1l;
        final String code = "code";
        final String displayName = "name";
        final String description = "description";
        final boolean isFederal = true;
        final int displayOrder = 1;
        final String system = "http://hl7.org/fhir/v3/ActCode";
        final ValueSetCategoryDto response = new ValueSetCategoryDto(id, code, displayName, description,
                isFederal, displayOrder, system);
        valueSetCategoryDtoList.add(response);
        when(valueSetLookupService.getValueSetCategories()).thenReturn(valueSetCategoryDtoList);

        // Act and Assert
        mvc.perform(get("/valueSetCategories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].code", is(code)))
                .andExpect(jsonPath("$[0].name", is(displayName)))
                .andExpect(jsonPath("$[0].description", is(description)))
                .andExpect(jsonPath("$[0].displayOrder", is(displayOrder)))
                .andExpect(jsonPath("$[0].federal", is(isFederal)))
                .andExpect(jsonPath("$[0].system", is(system)));
    }

    @Test
    public void testGetValueSetCategories_Throws_ValueSetCategoriesSearchFailedException() throws Exception {
        // Arrange
        List<ValueSetCategoryDto> valueSetCategoryDtoList = new ArrayList<>();
        final Long id = 1l;
        final String code = "code";
        final String displayName = "displayName";
        final String description = "description";
        final boolean isFederal = true;
        final int displayOrder = 1;
        final String system = "http://hl7.org/fhir/v3/ActCode";
        final ValueSetCategoryDto response = new ValueSetCategoryDto(id, code, displayName, description,
                isFederal, displayOrder, system);
        valueSetCategoryDtoList.add(response);
        when(valueSetLookupService.getValueSetCategories()).thenThrow(ValueSetCategoriesSearchFailedException.class);

        // Act and Assert
        mvc.perform(get("/valueSetCategories"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testSearchValueSetCategoryMaps() throws Exception {
        // Arrange
        List<CodedConceptAndCodeSystemOidDto> requestDtoList = new ArrayList<>();
        final String codedConceptCode = "codedConceptCode";
        final String codeSystemOid = "codeSystemOid";
        final CodedConceptAndCodeSystemOidDto request = new CodedConceptAndCodeSystemOidDto(codedConceptCode,
                codeSystemOid);
        requestDtoList.add(request);

        List<ValueSetCategoryMapDto> responseDtoList = new ArrayList<>();
        final String codedConceptCodeResponse = "codedConceptCode";
        final String codeSystemOidResponse = "codeSystemOid";
        final String code1 = "code1";
        final String code2 = "code2";
        final Set<String> valueSetCategoryCodes = new HashSet<String>(Arrays.asList(code1, code2));
        final ValueSetCategoryMapDto response = new ValueSetCategoryMapDto(codedConceptCodeResponse,
                codeSystemOidResponse, valueSetCategoryCodes);
        responseDtoList.add(response);

        when(valueSetLookupService.lookupValueSetCategoryMaps(argThat(matching(
                reqs -> reqs.equals(requestDtoList))
        ))).thenReturn(responseDtoList);

        // Act and Assert
        mvc.perform(post("/search/valueSetCategoryMaps")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsBytes(requestDtoList)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].codedConceptCode", is(codedConceptCode)))
                .andExpect(jsonPath("$[0].codeSystemOid", is(codeSystemOid)))
                .andExpect(jsonPath("$[0].valueSetCategoryCodes", hasItem(code1)))
                .andExpect(jsonPath("$[0].valueSetCategoryCodes", hasItem(code2)));
    }

    @Test
    public void testSearchValueSetCategoryMaps_Throws_ValueSetCategoryMapsSearchFailedException() throws Exception {
        // Arrange
        List<CodedConceptAndCodeSystemOidDto> requestDtoList = new ArrayList<>();
        final String codedConceptCode = "codedConceptCode";
        final String codeSystemOid = "codeSystemOid";
        final CodedConceptAndCodeSystemOidDto request = new CodedConceptAndCodeSystemOidDto(codedConceptCode,
                codeSystemOid);
        requestDtoList.add(request);

        List<ValueSetCategoryMapDto> responseDtoList = new ArrayList<>();
        final String codedConceptCodeResponse = "codedConceptCode";
        final String codeSystemOidResponse = "codeSystemOid";
        final String code1 = "code1";
        final String code2 = "code2";
        final Set<String> valueSetCategoryCodes = new HashSet<String>(Arrays.asList(code1, code2));
        final ValueSetCategoryMapDto response = new ValueSetCategoryMapDto(codedConceptCodeResponse,
                codeSystemOidResponse, valueSetCategoryCodes);
        responseDtoList.add(response);

        when(valueSetLookupService.lookupValueSetCategoryMaps(argThat(matching(
                reqs -> reqs.equals(requestDtoList))
        ))).thenThrow(ValueSetCategoryMapsSearchFailedException.class);

        // Act and Assert
        mvc.perform(post("/search/valueSetCategoryMaps")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsBytes(requestDtoList)))
                .andExpect(status().isInternalServerError());
    }
}