package gov.samhsa.c2s.vss.service.dto;

import lombok.Data;

@Data
public class ValueSetCategoryFieldsDto {

    /**
     * The code.
     */
    private String code;

    /**
     * The display name.
     */
    private String displayName;

    /**
     * The description.
     */
    private String description;

    /**
     * The isFederal.
     */
    private boolean isFederal;

    /**
     * The displayOrder.
     */
    private int displayOrder;
}