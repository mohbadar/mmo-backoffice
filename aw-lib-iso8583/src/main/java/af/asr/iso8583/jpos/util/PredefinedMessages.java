package af.asr.iso8583.jpos.util;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOUtil;
import org.jpos.q2.Q2;
import org.jpos.util.NameRegistrar;

public class PredefinedMessages {

    private static final String ECHO_TEST = "301";
    private final static String SIGN_ON = "001";
    private final static String SIGN_OFF = "002";
    private static final String NETWORK_REQUEST = "0800";
    public static final String INQUIRY_REQUEST = "0200";
    public static final String REVERSAL_REQUEST = "0400";
    public static final String INQUIRY_PROCESSING_CODE = "380000";
    public static final String PAYMENT_PROCESSING_CODE = "180000";
    public static final String MERCHANT_TYPE = "6021"; // bit 18
    public static final String BANK_BIN = "000730";// bit 32
    public static final String PRODUCT_POSTPAID_PLN = "2112";
    public static final String CURRENCY_CODE = "971"; // bit 49
    public static final String BILLING_PROVIDER_CODE = "214"; // bit 63
    private static final String CA_INFO_DATA = "0ADR21";


    //network  request message
    public static ISOMsg createNetworkRequest() {
        ISOMsg inqRequest = new ISOMsg();
        DateTime dateNow = new DateTime(DateTimeZone.UTC);

        String stan = StringUtils.leftPad(String.valueOf(new Random().nextInt(Integer.valueOf(ECHO_TEST))), 6, "0");
        DateTimeFormatter formatterBit7 = DateTimeFormat.forPattern("MMddhhmmss");

        try {
            inqRequest.setMTI(NETWORK_REQUEST);
            inqRequest.set(7, formatterBit7.print(dateNow));
            inqRequest.set(11, stan);
            inqRequest.set(70, ECHO_TEST);
            return inqRequest;
        } catch (Exception e) {
            Logger.getLogger(PredefinedMessages.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }


    //network signon request message
    public static ISOMsg createSingOn() {
        ISOMsg inqRequest = new ISOMsg();
        DateTime dateNow = new DateTime(DateTimeZone.UTC);

        String stan = StringUtils.leftPad(String.valueOf(new Random().nextInt(Integer.valueOf(ECHO_TEST))), 6, "0");
        DateTimeFormatter formatterBit7 = DateTimeFormat.forPattern("MMddhhmmss");

        try {
            inqRequest.setMTI(NETWORK_REQUEST);
            inqRequest.set(7, formatterBit7.print(dateNow));
            inqRequest.set(11, stan);
            inqRequest.set(70, SIGN_ON);
            return inqRequest;
        } catch (Exception e) {
            Logger.getLogger(PredefinedMessages.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }


    //network signoff request message
    public static ISOMsg createSingOff() {
        ISOMsg inqRequest = new ISOMsg();
        DateTime dateNow = new DateTime(DateTimeZone.UTC);

        String stan = StringUtils.leftPad(String.valueOf(new Random().nextInt(Integer.valueOf(ECHO_TEST))), 6, "0");
        DateTimeFormatter formatterBit7 = DateTimeFormat.forPattern("MMddhhmmss");

        try {
            inqRequest.setMTI(NETWORK_REQUEST);
            inqRequest.set(7, formatterBit7.print(dateNow));
            inqRequest.set(11, stan);
            inqRequest.set(70, SIGN_OFF);
            return inqRequest;
        } catch (Exception e) {
            Logger.getLogger(PredefinedMessages.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }



    //create postpaied inquiry message
    private static ISOMsg createPostpaidInquiryRequest() {
        ISOMsg inqRequest = new ISOMsg();
        DateTime dateNow = new DateTime();
        DateTime dateNowGMT = new DateTime(DateTimeZone.UTC);
        DateTime setelmentDate = dateNow.plusDays(1);
        DateTimeFormatter formatterBit7 = DateTimeFormat.forPattern("MMddhhmmss");
        DateTimeFormatter formatterBit12 = DateTimeFormat.forPattern("hhmmss");
        DateTimeFormatter formatterBit13 = DateTimeFormat.forPattern("MMdd");

        String pan = StringUtils.leftPad(BANK_BIN + formatterBit7.print(dateNowGMT), 16, "0");
        String stan = StringUtils.leftPad(String.valueOf(new Random().nextInt(Integer.valueOf(ECHO_TEST))), 6, "0");
        String idPel = "539507764112"; //"530170958192";
        String cardAcceptorId = StringUtils.rightPad("02906123", 15, " ");

        try {
            inqRequest.setMTI(INQUIRY_REQUEST);
            inqRequest.set(2, pan);
            inqRequest.set(3, INQUIRY_PROCESSING_CODE);
            inqRequest.set(4, StringUtils.leftPad("0", 12, "0"));
            inqRequest.set(7, formatterBit7.print(dateNowGMT));
            inqRequest.set(11, stan);
            inqRequest.set(12, formatterBit12.print(dateNow));
            inqRequest.set(13, formatterBit13.print(dateNow));
            inqRequest.set(14, formatterBit13.print(dateNow));
            inqRequest.set(15, formatterBit13.print(setelmentDate));
            inqRequest.set(18, MERCHANT_TYPE);
            inqRequest.set(32, StringUtils.leftPad(BANK_BIN, 6, "0"));
            inqRequest.set(37, stan + idPel.substring(idPel.length() - 6, idPel.length()));
            inqRequest.set(42, cardAcceptorId);

            StringBuilder sb = new StringBuilder();
            sb.append(PRODUCT_POSTPAID_PLN);// produk indikator
            sb.append(StringUtils.rightPad(idPel, 13, " "));// identitas pelanggan
            inqRequest.set(48, sb.toString());

            inqRequest.set(49, CURRENCY_CODE);
            inqRequest.set(63, BILLING_PROVIDER_CODE);
            return inqRequest;
        } catch (Exception ex) {
            Logger.getLogger(PredefinedMessages.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }


    //create post paied payment request message
    public static ISOMsg createPostpaidPaymentRequest(ISOMsg inqResponse) {
        ISOMsg paymentRequest = (ISOMsg) inqResponse.clone();
        DateTime dateNow = new DateTime();
        DateTime dateNowGMT = new DateTime(DateTimeZone.UTC);
        DateTimeFormatter formatterBit7 = DateTimeFormat.forPattern("MMddhhmmss");
        DateTimeFormatter formatterBit12 = DateTimeFormat.forPattern("hhmmss");

        String stan = StringUtils.leftPad(String.valueOf(new Random().nextInt(Integer.valueOf(ECHO_TEST))), 6, "0");
        String idPel = "539507764112"; //"530170958192";

        try {
            paymentRequest.setMTI(INQUIRY_REQUEST);
            paymentRequest.set(3, PAYMENT_PROCESSING_CODE);
//            paymentRequest.set(4, convertToProduct(inqResponse.getString(48)));
            paymentRequest.set(7, formatterBit7.print(dateNowGMT));
            paymentRequest.set(11, stan);
            paymentRequest.set(12, formatterBit12.print(dateNow));
            paymentRequest.set(37, stan + idPel.substring(idPel.length() - 6, idPel.length()));
//            paymentRequest.set(48, setDataBillingProvider(inqResponse.getString(48)));

            paymentRequest.unset(39);

            return paymentRequest;

        } catch (Exception ex) {
            Logger.getLogger(PredefinedMessages.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }


    //create reversal request message
    public static ISOMsg createReversalRequest(ISOMsg paymentReq) {
        ISOMsg reversalReq = (ISOMsg) paymentReq.clone();
        DateTime dateNow = new DateTime();
        DateTime dateNowGMT = new DateTime(DateTimeZone.UTC);

        DateTimeFormatter formatterBit7 = DateTimeFormat.forPattern("MMddhhmmss");
        DateTimeFormatter formatterBit12 = DateTimeFormat.forPattern("hhmmss");
        try {
            reversalReq.setMTI(REVERSAL_REQUEST);

            reversalReq.set(7, formatterBit7.print(dateNowGMT));
            reversalReq.set(12, formatterBit12.print(dateNow));

            final StringBuilder bit90 = new StringBuilder();
            bit90.append(paymentReq.getMTI());
            bit90.append(paymentReq.getString(11));
            bit90.append(paymentReq.getString(7));
            bit90.append(StringUtils.leftPad(paymentReq.getString(32), 11, "0"));
            bit90.append(StringUtils.rightPad("0", 11, "0"));

            reversalReq.set(90, bit90.toString());

            reversalReq.unset(39);
            return reversalReq;

        } catch (Exception ex) {
            Logger.getLogger(PredefinedMessages.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }



    private static String setDataBillingProvider(String bit48Inq) {
        StringBuilder builder = new StringBuilder();
        builder.append(bit48Inq.substring(0, 18).trim());
        builder.append(bit48Inq.substring(17, 18).trim());
        builder.append(bit48Inq.substring(18, 52));
        builder.append(StringUtils.rightPad(CA_INFO_DATA, 32, " "));
        builder.append(bit48Inq.substring(84));
        return builder.toString();
    }
}
