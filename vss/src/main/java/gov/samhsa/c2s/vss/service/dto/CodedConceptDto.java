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

	private Map<Long, String> valueSetMap = new HashMap<Long, String>();

	private Long valueSetId;
	private String valueSetName;

	private Long codeSystemId;
	private String codeSystemName;

	private Long codeSystemVersionId;
	private String codeSystemVersionName;
	
	private List<Long> valueSetIds;
	

	
	private List<Integer> listOfDuplicatesCodes;
	private int conceptCodesInserted;

	private String description;


}
