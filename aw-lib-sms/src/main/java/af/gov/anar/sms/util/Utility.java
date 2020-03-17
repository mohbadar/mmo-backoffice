package af.gov.anar.sms.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class Utility {

    private static final Logger LOGGER = LoggerFactory.getLogger(Utility.class);

    public static String[] parseResponse(String response) {
        LOGGER.info("Request parse response:{}", response);
        String [] parseResponse = new String[2];
        String status = response.substring(0, 1);
        String statusMessage=response.substring(3, response.length());
        parseResponse[0]=status;
        parseResponse[1]=statusMessage;
        LOGGER.info("Response after status:{} message:{}", status, statusMessage);
        return parseResponse;
    }

    public static String createUrl(HashMap<String, String> parameter) {
        StringBuilder url = new StringBuilder(ConstantLoader.BASE_URL);
        url.append("username=").append(ConstantLoader.USERNAME)
                .append("&password=").append(ConstantLoader.PASSWORD);
        parameter.keySet().forEach((param) -> {
            url.append("&").append(param).append("=").append(parameter.get(param));
        });
        return url.toString();
    }
}