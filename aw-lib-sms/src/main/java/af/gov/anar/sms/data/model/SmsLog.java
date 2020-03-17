package af.gov.anar.sms.data.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="sms_log")
@Getter
@NoArgsConstructor
public class SmsLog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "smslog_generator")
    @SequenceGenerator(name="smslog_generator", sequenceName = "smslog_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Setter
    private Date sendDate;
    private String senderMsisdn;
    private String receiverMsisdn;
    private String message;
    @Setter
    private Date deliveryDate;
    @Setter
    private int deliveryStatusNumber;
    @Setter
    private String deliveryStatusMessage;

    public SmsLog(Date sendDate, String senderMsisdn, String receiverMsisdn, String message) {
        this.sendDate = sendDate;
        this.senderMsisdn = senderMsisdn;
        this.receiverMsisdn = receiverMsisdn;
        this.message = message;
    }

    public SmsLog(Date sendDate, String senderMsisdn, String receiverMsisdn, String message, Date deliveryDate, int deliveryStatusNumber) {
        this.sendDate = sendDate;
        this.senderMsisdn = senderMsisdn;
        this.receiverMsisdn = receiverMsisdn;
        this.message = message;
        this.deliveryDate = deliveryDate;
        this.deliveryStatusNumber = deliveryStatusNumber;
    }

}