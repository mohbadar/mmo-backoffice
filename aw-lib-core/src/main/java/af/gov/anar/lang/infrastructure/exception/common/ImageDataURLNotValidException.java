
package af.gov.anar.lang.infrastructure.exception.common;

public class ImageDataURLNotValidException extends AbstractPlatformDomainRuleException {

    public ImageDataURLNotValidException() {
        super("error.msg.dataURL.save", "Only GIF, PNG and JPEG Data URL's are allowed");
    }
}
