package af.gov.anar.lib.cryptojce.core;

import af.gov.anar.lib.cryptojce.spi.JwsSpec;
import af.gov.anar.lib.cryptojce.util.JWSValidation;

import java.security.PrivateKey;
import java.security.cert.X509Certificate;

public class JwsFactory {
    public JwsSpec<String, String , X509Certificate,PrivateKey> getJWS(){
        return new JWSValidation();
    }
}
