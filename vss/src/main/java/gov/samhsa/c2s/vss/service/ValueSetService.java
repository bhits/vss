package gov.samhsa.c2s.vss.service;

import gov.samhsa.c2s.vss.domain.ValueSet;
import gov.samhsa.c2s.vss.service.dto.ValueSetDto;
import gov.samhsa.c2s.vss.service.dto.ValueSetUploadDto;
import gov.samhsa.c2s.vss.service.exception.ValueSetNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * The Interface ValueSetService.
 */

@Transactional
public interface ValueSetService {

    /**
     * Creates a new person.
     *
     * @param valueSetDto The information of the created person.
     * @return The created person.
     * @throws ValueSetNotFoundException the value set category not found exception
     */
    public ValueSetDto createValueSet(ValueSetDto valueSetDto)
            throws ValueSetNotFoundException;


    public ValueSet getValueSet(ValueSetUploadDto valueSetUploadDto);

    /**
     * Deletes a ValueSet.
     *
     * @param valueSetId the value set id
     * @return The deleted ValueSet.
     * @throws ValueSetNotFoundException if no ValueSet is found with the given id.
     */
    public ValueSetDto deleteValueSet(Long valueSetId) throws ValueSetNotFoundException;

    /**
     * Finds all ValueSets.
     *
     * @return A list of ValueSets.
     */
    @Transactional(readOnly = true)
    public List<ValueSetDto> findAll();

    /**
     * Find all by page number.
     *
     * @param pageNumber the page number
     * @return the list
     */
    @Transactional(readOnly = true)
    public Map<String, Object> findAll(int pageNumber);

    /**
     * Finds ValueSet by id.
     *
     * @param id The id of the wanted ValueSet.
     * @return The found ValueSet. If no ValueSet is found, this method returns
     * null.
     * @throws ValueSetNotFoundException the value set category not found exception
     */
    @Transactional(readOnly = true)
    public ValueSetDto findById(Long id)
            throws ValueSetNotFoundException;

    /**
     * Updates the information of a ValueSet.
     *
     * @param updated The information of the updated ValueSet.
     * @return The updated ValueSet.
     * @throws ValueSetNotFoundException if no ValueSet is found with given id.
     * @throws ValueSetNotFoundException the value set category not found exception
     */
    public ValueSetDto updateValueSet(ValueSetDto updated)
            throws ValueSetNotFoundException, ValueSetNotFoundException;


    /**
     * Find all by name.
     *
     * @param searchTerm the search term
     * @param ValueSet   the value set category
     * @param pageNumber the page number
     * @return the map
     */
    @Transactional(readOnly = true)
    public Map<String, Object> findAllByName(String searchTerm,
                                             String ValueSet, int pageNumber);

    /**
     * Find by code.
     *
     * @param searchTerm the search term
     * @param ValueSet   the value set category
     * @param pageNumber the page number
     * @return the map
     */
    @Transactional(readOnly = true)
    public Map<String, Object> findAllByCode(String searchTerm,
                                             String ValueSet, int pageNumber);

    /**
     * Value set batch upload.
     *
     * @param valueSetDto the value set dto
     * @param file        the bytes
     * @return the value set dto
     * @throws Exception the exception
     */
    public ValueSetDto valueSetBatchUpload(ValueSetDto valueSetDto,
                                           MultipartFile file) throws Exception;

    /**
     * Find all without deletable.
     *
     * @return the list
     */
    @Transactional(readOnly = true)
    public List<ValueSetDto> findAllWithoutDeletable();

}

