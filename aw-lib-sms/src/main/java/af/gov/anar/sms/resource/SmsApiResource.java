package af.gov.anar.sms.resource;

import af.gov.anar.sms.data.model.SmsLog;
import af.gov.anar.sms.exception.ServiceException;
import af.gov.anar.sms.service.ISmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/sms")
public class SmsApiResource {


    @Autowired
    private ISmsService service;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<SmsLog> getLogs() throws ServiceException {
        return service.getLogs();
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public SmsLog sendSms(@RequestBody SmsLog sms) throws ServiceException {
        return service.sendSms(sms);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public SmsLog delete(@PathVariable(value = "id") Long id) throws ServiceException {
        return service.deleteLogs(id);
    }

    @RequestMapping(value = "/deleteall", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public int deleteAll() throws ServiceException {
        return service.clearAllLogs();
    }
}
