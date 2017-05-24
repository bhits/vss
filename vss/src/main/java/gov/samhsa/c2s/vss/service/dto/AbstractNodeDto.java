package gov.samhsa.c2s.vss.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbstractNodeDto {
	
	protected Long id;
	@NotEmpty
	protected String code;
    
	protected String name;
	
	protected String userName;

	public  boolean isError;
	protected String errorMessage;
	protected String successMessage;
	
	protected boolean deletable = true;
	

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}	
	
}
