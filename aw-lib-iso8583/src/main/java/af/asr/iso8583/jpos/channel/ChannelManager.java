package af.asr.iso8583.jpos.channel;


import org.jpos.iso.ISOMsg;
import org.jpos.iso.MUX;
import org.jpos.q2.QBeanSupport;
import org.jpos.util.NameRegistrar;

public class ChannelManager extends QBeanSupport {
    private long MAX_TIME_OUT;
    private MUX mux;

    @Override
    protected void initService() throws Exception {
        super.initService();
        log.info("initializing jpos-client Service...");

        try {
            mux = (MUX) NameRegistrar.get("mux." + cfg.get("mux"));
            MAX_TIME_OUT = cfg.getLong("timeout");
            NameRegistrar.register("jpos-client-manager", this);
            log.info("jpos-client is ready!");
        } catch (Exception e) {
            log.error(e.getSuppressed(), e);
        }

    }

    public ISOMsg sendRequest(ISOMsg m) throws Exception {
        if (m == null) {
            return null;
        }
        return sendRequest(m, mux, MAX_TIME_OUT);
    }

    private ISOMsg sendRequest(ISOMsg request, MUX mux, long time) throws Exception {

        if (mux != null) {
            long start = System.currentTimeMillis();
            ISOMsg response = mux.request(request, time);
            long duration = System.currentTimeMillis() - start;
            log.info("Response time (ms):" + duration);
            return response;
        }
        return null;
    }

}