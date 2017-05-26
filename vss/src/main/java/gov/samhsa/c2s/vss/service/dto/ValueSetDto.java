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
public class ValueSetDto extends CodeNameDto {

	/** The description. */
	private String description;
	
	private Map<Long,String> valueSetCategoryMap =  new HashMap<Long, String>();

	
	/** The value set cat code. */
	private String valueSetCatCode;

	/* (non-Javadoc)
	 * @see gov.samhsa.consent2share.service.dto.CodeNameDto#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
