
package af.gov.anar.lang.data;

public class EmailDetail {

    private final String subject;
    private final String body;
    private final String address;
    private final String contactName;

    public EmailDetail(final String subject, final String body, final String address, final String contactName) {
        this.subject = subject;
        this.body = body;
        this.address = address;
        this.contactName = contactName;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public String getContactName() {
        return this.contactName;
    }

    public String getAddress() {
        return this.address;
    }
}