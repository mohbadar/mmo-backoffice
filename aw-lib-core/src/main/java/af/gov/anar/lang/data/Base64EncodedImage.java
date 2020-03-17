
package af.gov.anar.lang.data;

public class Base64EncodedImage {

    private final String base64EncodedString;
    private final String fileExtension;

    public Base64EncodedImage(final String base64EncodedString, final String fileExtension) {
        this.base64EncodedString = base64EncodedString;
        this.fileExtension = fileExtension;
    }

    public String getBase64EncodedString() {
        return this.base64EncodedString;
    }

    public String getFileExtension() {
        return this.fileExtension;
    }
}