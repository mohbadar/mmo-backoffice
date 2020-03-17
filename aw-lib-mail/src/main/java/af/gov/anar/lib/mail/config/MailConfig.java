package af.gov.anar.lib.mail.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.annotation.Resource;
import java.util.Properties;


@Configuration
@PropertySource(value = {"classpath:mail.properties"})
public class MailConfig{

    private static final Logger logger = LoggerFactory.getLogger(MailConfig.class);

    @Resource
    private Environment environment;

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        Properties mailProperties = new Properties();
        mailProperties.put("mail.debug", this.environment.getProperty("mail.debug"));
        mailProperties.put("mail.smtp.auth", this.environment.getProperty("mail.smtp.auth"));
        mailProperties.put("mail.smtp.starttls.enable", this.environment.getProperty("mail.smtp.starttls.enable"));
        mailSender.setJavaMailProperties(mailProperties);
        mailSender.setHost(this.environment.getProperty("mail.host"));
        mailSender.setPort(Integer.parseInt(this.environment.getProperty("mail.port")));
        mailSender.setProtocol(this.environment.getProperty("mail.protocol"));
        mailSender.setUsername(this.environment.getProperty("mail.username"));
        mailSender.setPassword(this.environment.getProperty("mail.password"));

        return mailSender;
    }
}