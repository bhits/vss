package gov.samhsa.c2s.vss.service;

import gov.samhsa.c2s.vss.service.dto.CodeSystemDto;
import gov.samhsa.c2s.vss.service.dto.CodeSystemVersionDto;
import gov.samhsa.c2s.vss.service.exception.CodeSystemNotFoundException;
import gov.samhsa.c2s.vss.service.exception.CodeSystemVersionNotFoundException;

import java.util.List;

public interface CodeSystemVersionService {
    /**
     * Creates a new CodeSystem.
     * @param codeSystemVersionDto The information of the created person.
     * @return  The created person.
     */
    public CodeSystemVersionDto createCodeSystemVersion(CodeSystemVersionDto codeSystemVersionDto);

    /**
     * Deletes a CodeSystem.
     * @param CodeSystemVersionId  The id of the deleted CodeSystem.
     * @return  The deleted CodeSystem.
     * @throws CodeSystemNotFoundException  if no CodeSystem is found with the given id.
     */
    public CodeSystemVersionDto deleteCodeSystemVersion(Long CodeSystemVersionId) throws CodeSystemVersionNotFoundException;

    /**
     * Finds all CodeSystemVersions.
     * @return  A list of CodeSystemVersions.
     */
    public List<CodeSystemVersionDto> findAll();

    /**
     * Finds CodeSystemVersion by id.
     * @param id    The id of the wanted CodeSystemVersion.
     * @return  The found CodeSystemVersion. If no CodeSystemVersion is found, this method returns null.
     */
    public CodeSystemVersionDto findById(Long id);

    /**
     * Updates the information of a CodeSystemVersion.
     * @param updated   The information of the updated CodeSystemVersion.
     * @return  The updated CodeSystemVersion.
     * @throws CodeSystemVersionNotFoundException  if no CodeSystemVersion is found with given id.
     */
    public CodeSystemVersionDto updateCodeSystemVersion(CodeSystemVersionDto updated) throws CodeSystemVersionNotFoundException;

}
