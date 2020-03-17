package af.gov.anar.sms.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;



@Configuration
public class ConstantLoader {


    public static String BASE_URL = "http://localhost:13013/cgi-bin/sendsms?";
    public static String USERNAME = "badar";
    public static String PASSWORD = "tester";


    @Value("${anar-kannel.sms.base-url}")
    public void setBaseUrl (String baseUrl) {
        BASE_URL = baseUrl;
    }


    @Value("${anar-kannel.sms.username}")
    public void setUsername (String username) {
        USERNAME = username;
    }

    @Value("${anar-kannel.sms.password}")
    public void setPassword (String password) {
        PASSWORD = password;
    }



}