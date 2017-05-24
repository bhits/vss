package gov.samhsa.c2s.vss.service;

import gov.samhsa.c2s.vss.service.dto.CodeSystemDto;
import gov.samhsa.c2s.vss.service.exception.CodeSystemNotFoundException;

import java.util.List;

public interface CodeSystemService {
    /**
     * Creates a new CodeSystem.
     * @param codeSystemDto The information of the created person.
     * @return  The created person.
     */
    public CodeSystemDto createCodeSystem(CodeSystemDto codeSystemDto);

    /**
     * Deletes a CodeSystem.
     * @param codeSystemId  The id of the deleted CodeSystem.
     * @return  The deleted CodeSystem.
     * @throws CodeSystemNotFoundException  if no CodeSystem is found with the given id.
     */
    public CodeSystemDto deleteCodeSystem(Long codeSystemId) throws CodeSystemNotFoundException;

    /**
     * Finds all CodeSystems.
     * @return  A list of CodeSystems.
     */
    public List<CodeSystemDto> findAll();

    /**
     * Finds CodeSystem by id.
     * @param id    The id of the wanted CodeSystem.
     * @return  The found CodeSystem. If no CodeSystem is found, this method returns null.
     */
    public CodeSystemDto findById(Long id);

    /**
     * Updates the information of a CodeSystem.
     * @param updated   The information of the updated CodeSystem.
     * @return  The updated CodeSystem.
     * @throws CodeSystemNotFoundException  if no CodeSystem is found with given id.
     */
    public CodeSystemDto updateCodeSystem(CodeSystemDto updated) throws CodeSystemNotFoundException;

}
