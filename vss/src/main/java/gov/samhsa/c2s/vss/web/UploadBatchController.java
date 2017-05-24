package gov.samhsa.c2s.vss.web;

import gov.samhsa.c2s.vss.service.batch.BatchJobManagerService;
import gov.samhsa.c2s.vss.service.dto.UploadRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
public class UploadBatchController {

    @Autowired
    @Resource(name ="valueSetJobManager" )
    private BatchJobManagerService valueSetJobManagerService;

    @Autowired
    @Resource(name ="codedConceptJobManager" )
    private BatchJobManagerService codedConceptJobManagerService;



    @RequestMapping(value = "/valueSet/batchUpload", method = RequestMethod.POST)
    public void batchValueSetUpload(@Valid @RequestBody UploadRequestDto uploadRequestDto) throws Exception {
        valueSetJobManagerService.batchUpload(uploadRequestDto);
    }

    @RequestMapping(value = "/codedConcept/batchUpload", method = RequestMethod.POST)
    public void batchCodedConceptUpload(@Valid @RequestBody UploadRequestDto uploadRequestDto) throws Exception {
        codedConceptJobManagerService.batchUpload(uploadRequestDto);
    }


}
