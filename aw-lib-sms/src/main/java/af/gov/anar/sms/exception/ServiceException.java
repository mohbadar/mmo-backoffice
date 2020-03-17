package af.gov.anar.sms.exception;


import af.gov.anar.lang.infrastructure.exception.common.BaseCheckedException;

public class ServiceException extends BaseCheckedException {

    public ServiceException(String message) {
        super(message);
    }
}
