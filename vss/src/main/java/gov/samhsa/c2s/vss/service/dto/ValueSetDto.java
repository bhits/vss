package gov.samhsa.c2s.vss.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class ValueSetDto.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValueSetDto extends AbstractNodeDto {

	/** The description. */
	private String description;
	
	/** The value set category id. */
	private Long valueSetCategoryId;
	
	/** The value set cat name. */
	private String valueSetCatName;
	
	private Map<Long,String> valueSetCategoryMap =  new HashMap<Long, String>();

	
	/** The value set cat code. */
	private String valueSetCatCode;
	/** The rows updated. */
	private int rowsUpdated;
	
	/** The error row. */
	private int errorRow;


	/* (non-Javadoc)
	 * @see gov.samhsa.consent2share.service.dto.AbstractNodeDto#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
