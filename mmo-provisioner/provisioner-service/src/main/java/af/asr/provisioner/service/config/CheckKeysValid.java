package af.asr.provisioner.service.config;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

public class CheckKeysValid implements ConstraintValidator<KeysValid, SystemProperties> {

    @Override
    public void initialize(KeysValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(final SystemProperties value, final ConstraintValidatorContext context) {
        if (value.getPrivateKey().getModulus() == null || value.getPrivateKey().getExponent() == null ||
                value.getPublicKey().getModulus() == null ||value.getPublicKey().getExponent() == null)
            return false;

        try {
            final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            final RSAPrivateKeySpec rsaPrivateKeySpec
                    = new RSAPrivateKeySpec(value.getPrivateKey().getModulus(), value.getPrivateKey().getExponent());
            final PrivateKey privateKey = keyFactory.generatePrivate(rsaPrivateKeySpec);

            final RSAPublicKeySpec rsaPublicKeySpec
                    = new RSAPublicKeySpec(value.getPublicKey().getModulus(), value.getPublicKey().getExponent());
            final PublicKey publicKey = keyFactory.generatePublic(rsaPublicKeySpec);

            final Signature signature = Signature.getInstance("NONEwithRSA");
            signature.initSign(privateKey);
            final byte[] signed = signature.sign();

            signature.initVerify(publicKey);
            return signature.verify(signed);
        } catch (final NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException | SignatureException e) {
            return false;
        }
    }
}