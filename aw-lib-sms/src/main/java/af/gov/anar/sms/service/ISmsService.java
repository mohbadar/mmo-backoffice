package af.gov.anar.sms.service;


import af.gov.anar.sms.data.model.SmsLog;
import af.gov.anar.sms.exception.ServiceException;

public interface ISmsService {

    public SmsLog sendSms(SmsLog send) throws ServiceException;

    public Iterable<SmsLog> getLogs() throws ServiceException;

    public SmsLog deleteLogs(Long id) throws ServiceException;

    public int clearAllLogs() throws ServiceException ;

}
