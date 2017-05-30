package gov.samhsa.c2s.vss.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodedConceptDto extends CodeNameDto {
	//batch upload parameters
	private Long codeSystemId;
	private Long codeSystemVersionId;
	private List<Long> valueSetIds;
	private String description;

}
