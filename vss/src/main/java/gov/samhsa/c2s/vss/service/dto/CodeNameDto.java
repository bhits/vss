package gov.samhsa.c2s.vss.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeNameDto {
	
	protected Long id;
	@NotEmpty
	protected String code;
    
	protected String name;


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}	
	
}
