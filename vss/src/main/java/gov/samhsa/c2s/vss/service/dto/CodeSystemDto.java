package gov.samhsa.c2s.vss.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeSystemDto extends CodeNameDto {

    
    private String displayName;
    
    private List<ValueSetDto> valueSets = new ArrayList<ValueSetDto>();

    @NotEmpty
    private String codeSystemOid;

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
	
}
