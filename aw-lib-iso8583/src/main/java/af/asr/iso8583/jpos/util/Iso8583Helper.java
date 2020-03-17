package af.asr.iso8583.jpos.util;

import java.math.BigInteger;
import java.util.Map;

public class Iso8583Helper {
    public static BigInteger getBitmap(Map<Integer, String> message){
        BigInteger bitmap = BigInteger.ZERO;

        for (Integer de : message.keySet()) {
            if(de > 64){
                bitmap = bitmap.setBit(128 - 1);
            }
            bitmap = bitmap.setBit(128 - de);
        }

        return bitmap;
    }

    public static String messageString(String mti, Map<Integer, String> message){
        StringBuilder hasil = new StringBuilder();
        hasil.append(mti);
        hasil.append(getBitmap(message).toString(16));
        for (Integer de : message.keySet()) {
            hasil.append(message.get(de));
        }
        return hasil.toString();
    }
}