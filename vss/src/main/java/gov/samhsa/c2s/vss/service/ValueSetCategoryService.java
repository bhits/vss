package gov.samhsa.c2s.vss.service;

import gov.samhsa.c2s.vss.domain.ValueSetCategory;
import gov.samhsa.c2s.vss.service.dto.ValueSetCategoryDto;
import gov.samhsa.c2s.vss.service.exception.ValueSetCategoryNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ValueSetCategoryService {
    /**
     * Creates a new ValueSetCategory.
     * @param created   The information of the created ValueSetCategory.
     * @return  The created ValueSetCategory.
     */
    public ValueSetCategoryDto create(ValueSetCategoryDto created);

    /**
     * Deletes a ValueSetCategory.
     * @param ValueSetCategoryId  The id of the deleted ValueSetCategory.
     * @return  The deleted ValueSetCategory.
     * @throws ValueSetCategoryNotFoundException  if no ValueSetCategory is found with the given id.
     */
    public ValueSetCategoryDto delete(Long ValueSetCategoryId) throws ValueSetCategoryNotFoundException;

    /**
     * Finds all ValueSetCategories.
     * @return  A list of ValueSetCategories.
     */
    @Transactional(readOnly = true)
    public List<ValueSetCategoryDto> findAll();

    /**
     * Finds ValueSetCategory by id.
     * @param id    The id of the wanted ValueSetCategory.
     * @return  The found ValueSetCategory. If no ValueSetCategory is found, this method returns null.
     */
    @Transactional(readOnly = true)
    public ValueSetCategoryDto findById(Long id);

    /**
     * Finds ValueSetCategory by id.
     * @param code  The code of the wanted ValueSetCategory.
     * @return  The found ValueSetCategory. If no ValueSetCategory is found, this method returns null.
     */
    @Transactional(readOnly = true)
    public ValueSetCategoryDto findByCode(String code) throws ValueSetCategoryNotFoundException;

    /**
     * Updates the information of a ValueSetCategory.
     * @param updated   The information of the updated ValueSetCategory.
     * @return  The updated ValueSetCategory.
     * @throws ValueSetCategoryNotFoundException  if no ValueSetCategory is found with given id.
     */
    public ValueSetCategoryDto update(ValueSetCategoryDto updated) throws ValueSetCategoryNotFoundException;


}
