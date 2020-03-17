package af.gov.anar.sms.service;

import af.gov.anar.sms.data.model.SmsLog;
import af.gov.anar.sms.data.repository.SmsRepository;
import af.gov.anar.sms.exception.ServiceException;
import af.gov.anar.sms.util.Utility;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Optional;


@Service
@Log4j2
public class SmsServiceImpl implements ISmsService {

    @Autowired
    private SmsRepository repository;
    private RestTemplate template;

    public SmsServiceImpl() {
        template = new RestTemplate();
    }

    @Override
    public Iterable<SmsLog> getLogs() throws ServiceException {
        return repository.findAll();
    }

    @Override
    public SmsLog sendSms(SmsLog send) throws ServiceException{
        try {
            log.info("Request send sms from:{} to:{}", send.getSenderMsisdn(), send.getReceiverMsisdn());
            send.setSendDate(new Date());

//        Insert the log before requesting kennal
            SmsLog saved = repository.save(send);

            HashMap<String, String> parameter = new HashMap<>();
            parameter.put("to", send.getReceiverMsisdn());
            parameter.put("from", send.getSenderMsisdn());
            parameter.put("text", send.getMessage());

//     Generating the url for get request with append the parameter
            String url = Utility.createUrl(parameter);

            String response = template.getForObject(url, String.class);
            String[] parseResponse = Utility.parseResponse(response);
            saved.setDeliveryStatusNumber(Integer.valueOf(parseResponse[0]));
            saved.setDeliveryStatusMessage(parseResponse[1]);
            saved.setDeliveryDate(new Date());

            //Update the log after response arrived;
            SmsLog updated = repository.save(saved);

            return updated;
        }catch(Exception ex) {
            throw new ServiceException(ex.getMessage());
        }

    }

    @Override
    public SmsLog deleteLogs(Long id) throws ServiceException {
        try {
            Optional<SmsLog> response = repository.findById(id);
            if (response.isPresent()) {
                repository.delete(response.get());
                return response.get();
            } else {
                throw new Exception("Not Found");
            }
        } catch (Exception ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    @Override
    public int clearAllLogs() throws ServiceException {
        try {
            repository.deleteAll();
            return 1;
        } catch (Exception ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

}