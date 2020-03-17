package af.gov.anar.lib.mail.service;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class MailBuilder {

    private static final Logger logger = LoggerFactory.getLogger(MailBuilder.class);

    private String subject;
    private StringBuilder text;
    private List<Attachment> attachments;
    private String from;
    private Set<String> to;
    private Set<String> cc;
    private Set<String> bcc;


    /**
     * to set/append text to the mail
     * @param text wanted to set to the mail
     */
    public void appendToText(String text) {
        if(this.text == null)
            this.text = new StringBuilder();
        if(StringUtils.hasLength(text))
            this.text.append(text);
    }

    /**
     * to add an attachment to the mail
     * @param filePath path of the file wanted to attach to the mail
     * @param attachmentName will be shown as attached file name in the mail. It is an optional parameter
     * @return a boolean which refers adding file as an attachment is a success or not
     */
    public boolean addAttachment(String filePath, String attachmentName) {
        Assert.hasText(filePath, "Path cannot be null or empty");

        if(this.attachments == null)
            this.attachments = new LinkedList<>();

        File file = new File(filePath);
        Assert.notNull(file, "File object could not be created from path");

        String name = file.getName();


        if(StringUtils.hasText(attachmentName)) {
            char separator = '.';
            String extension;
            int extensionIndex = name.lastIndexOf(separator);
            if (extensionIndex > 0) {
                extension = separator + name.substring(extensionIndex + 1);
                name = attachmentName + extension;
            } else {
                name = attachmentName;
            }
        }

        this.attachments.add(new Attachment(name, file));

        return true;
    }

    /**
     * to add a To mail address to the mail
     * @param to mail wanted to add to the To field
     */
    public void addTo(String to) {
        if(this.to == null)
            this.to = new HashSet<>();
        if(StringUtils.hasLength(to))
            this.to.add(to);
    }

    /**
     * to add a Cc mail address to the mail
     * @param cc mail wanted to add to the Cc field
     */
    public void addCc(String cc) {
        if(this.cc == null)
            this.cc = new HashSet<>();
        if(StringUtils.hasLength(cc))
            this.cc.add(cc);
    }

    /**
     * to add a Bcc mail address to the mail
     * @param bcc mail wanted to add to the Bcc field
     */
    public void addBcc(String bcc) {
        if(this.bcc == null)
            this.bcc = new HashSet<>();
        if(StringUtils.hasLength(bcc))
            this.bcc.add(bcc);
    }

    public String toString() {
        return new StringBuilder()
                .append("subject: ").append(getSubject())
                .append(", text: ").append(getText())
                .append(", attachment: ").append(getAttachments())
                .append(", from: ").append(getFrom())
                .append(", to: ").append(getTo())
                .append(", cc: ").append(getCc())
                .append(", bcc: ").append(getBcc()).toString();
    }
}

@Setter
@Getter
class Attachment {

    private String name;
    private File file;

    Attachment(String name, File file) {
        this.name = name;
        this.file = file;
    }

    public String toString() {
        return new StringBuilder()
                .append("Name: ").append(getName())
                .append(", File: ").append(getFile()).toString();
    }
}