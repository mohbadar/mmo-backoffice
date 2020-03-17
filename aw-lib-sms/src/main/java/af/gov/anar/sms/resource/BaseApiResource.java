package af.gov.anar.sms.resource;

import af.gov.anar.sms.exception.ServiceException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
@Log4j2
public class BaseApiResource {


    @ExceptionHandler({ServiceException.class, HttpMessageNotReadableException.class, HttpRequestMethodNotSupportedException.class, MethodArgumentTypeMismatchException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Message handleServiceException(Exception ex) {
        log.info("Request handleServiceException to translate exception to readable form");
        Message message = new Message();
        message.setResult("error");
        message.setMessage(ex.getMessage());
        return message;
    }


    private class Message {

        @Getter
        @Setter
        private String result;
        @Getter
        @Setter
        private String message;

    }
}