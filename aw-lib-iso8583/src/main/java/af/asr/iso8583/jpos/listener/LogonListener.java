package af.asr.iso8583.jpos.listener;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISORequestListener;
import org.jpos.iso.ISOSource;

import java.io.IOException;

public class LogonListener implements ISORequestListener {
    @Override
    public boolean process(ISOSource source, ISOMsg m) {
        try {
            ISOMsg response = (ISOMsg) m.clone();
            if("0800".equals(m.getMTI())){
                response.setMTI("0810");
            } else if ("0200".equals(m.getMTI())) {
                response.setMTI("0210");
            }
            response.set(39,"00");
            //response.set(48, "UPAYPLT                   081932165008");
            source.send(response);
        } catch (ISOException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}