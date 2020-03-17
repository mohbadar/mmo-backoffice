package af.asr.iso8583.jpos.channel;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISORequestListener;
import org.jpos.iso.ISOSource;

public class RequestListener implements ISORequestListener {

    public static final String INQUIRY_REQUEST = "0200";
    public static final String INQUIRY_RESPONSE = "0210";
    private static final String NETWORK_REQUEST = "0800";
    private static final String NETWORK_RESPONSE = "0810";
    public static final String REVERSAL_REQUEST = "0400";
    public static final String REVERSAL_REQUEST_REPEAT = "0401";
    public static final String REVERSAL_RESPONSE = "0410";

    public static final Integer RESPONSE_CODE = 39;

    public static final String INQUIRY_PROCESSING_CODE = "380000"; // bit 3
    public static final String PAYMENT_PROCESSING_CODE = "180000";

    @Override
    public boolean process(ISOSource sender, ISOMsg request) {
        try {
            String mti = request.getMTI();

            DateTime dateNow = new DateTime(DateTimeZone.UTC);
            DateTimeFormatter formatterBit7 = DateTimeFormat.forPattern("MMddhhmmss");

            if (NETWORK_REQUEST.equals(mti)) {
                ISOMsg response = (ISOMsg) request.clone();
                response.setMTI(NETWORK_RESPONSE);
                response.set(RESPONSE_CODE, "00");
                response.set(7, formatterBit7.print(dateNow));
                sender.send(response);
                return true;
            }

            if (REVERSAL_RESPONSE.equals(mti)) {
                Logger.getLogger(RequestListener.class.getName()).log(Level.SEVERE, "late response is coming");
            }

            return false;
        } catch (Exception ex) {
            Logger.getLogger(RequestListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}