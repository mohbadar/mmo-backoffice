package af.gov.anar.lib.mail.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.mail.internet.MimeMessage;


@Service
public class MailService {

    private static final Logger logger = LoggerFactory.getLogger(MailService.class);
    private static final boolean DEFAULT_MULTIPART = true;
    private static final String DEFAULT_ENCODING = "UTF-8";

    @Autowired
    private JavaMailSender mailSender;

    /**
     * to send a mail
     * @param mailBuilder a MailBuilder object which holds features of mail wanted to be sent
     * @return a boolean value which tells sending process is a success or not
     * @throws javax.mail.MessagingException if an error occurs while creating mime message for mail
     * @throws MailException if an error occurs while sending mail
     */
    public boolean send(MailBuilder mailBuilder)
            throws MailException, javax.mail.MessagingException {
        return this.send(mailBuilder, DEFAULT_MULTIPART, DEFAULT_ENCODING);
    }

    public boolean send(MailBuilder mailBuilder, boolean multipart)
            throws MailException, javax.mail.MessagingException {
        return this.send(mailBuilder, multipart, DEFAULT_ENCODING);
    }

    public boolean send(MailBuilder mailBuilder, boolean multipart, String encoding)
            throws MailException, javax.mail.MessagingException {
        Assert.notNull(mailBuilder, "'mailBuilder' cannot be null");
        Assert.hasText(mailBuilder.getFrom(), "'from' cannot be null");
        Assert.notEmpty(mailBuilder.getTo(), "'to' cannot be null or empty");
        Assert.notNull(mailBuilder.getSubject(), "'subject' cannot be null");

        MimeMessage mail = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail, multipart, encoding);

        helper.setFrom(mailBuilder.getFrom());

        for(String to : mailBuilder.getTo()) {
            helper.addTo(to);
        }

        helper.setSubject(mailBuilder.getSubject());

        if(!CollectionUtils.isEmpty(mailBuilder.getCc())) {
            for (String cc : mailBuilder.getCc()) {
                helper.addCc(cc);
            }
        }

        if(!CollectionUtils.isEmpty(mailBuilder.getBcc())) {
            for (String bcc : mailBuilder.getBcc()) {
                helper.addBcc(bcc);
            }
        }

        if(mailBuilder.getText() != null) {
            helper.setText(mailBuilder.getText().toString(), true);
        }

        if(!CollectionUtils.isEmpty(mailBuilder.getAttachments())) {
            for(Attachment attachment : mailBuilder.getAttachments()) {
                helper.addAttachment(attachment.getName(), attachment.getFile());
            }
        }

        this.mailSender.send(mail);

        return true;
    }
}