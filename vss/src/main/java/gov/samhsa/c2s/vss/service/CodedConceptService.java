package gov.samhsa.c2s.vss.service;

import gov.samhsa.c2s.vss.service.dto.CodedConceptDto;

public interface CodedConceptService {
    /**
     * Creates a new ValueSetCategory.
     * @param codedConceptDto   The information of the created ValueSetCategory.
     * @return  The created ValueSetCategory.
     */
    public CodedConceptDto createCodedConcept(CodedConceptDto codedConceptDto);
}
