package gov.samhsa.c2s.vss.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodedConceptUploadDto extends UploadResponseDto  implements Serializable  {
    //batch upload parameters
    private Long codeSystemId;
    private Long codeSystemVersionId;
    private String uploadValueSets;
}
