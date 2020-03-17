package af.asr.iso8583.jpos;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import af.asr.iso8583.jpos.channel.ChannelManager;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOUtil;
import org.jpos.iso.MUX;
import org.jpos.q2.Q2;
import org.jpos.util.NameRegistrar;


public class ClientInit {


    public static ISOMsg start(ISOMsg msg) throws Exception {

        Q2 client = new Q2();
        client.start();

        // wait until connection established
        ISOUtil.sleep(3000);

//        ChannelManager channelManager = (ChannelManager) NameRegistrar.get("jpos-client-manager");
//        ISOMsg response = channelManager.sendRequest(msg);
//        return response;

        // simple sending iso message using qmux without Channel Manager
        // remove 30_client_channel_manager.xml

        MUX mux = (MUX) NameRegistrar.get ("mux.jpos-client-mux");
        ISOMsg response = mux.request(msg, 30000);
        System.out.println("Response : "+new String(response.pack()));
        return response;

    }

}