package gov.samhsa.c2s.vss.service.batch;

import gov.samhsa.c2s.vss.service.dto.UploadRequestDto;

public interface BatchJobManagerService {

    /**
     * Value set batch upload.
     * @param uploadRequestDto
     * @return the value set dto
     * @throws Exception
     *             the exception
     */
    public void batchUpload(UploadRequestDto uploadRequestDto) throws Exception;


}
