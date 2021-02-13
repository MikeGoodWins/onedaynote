package online.onedaynote.api.services.implementations;

import java.time.format.DateTimeFormatter;
import java.util.Objects;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import online.onedaynote.api.dao.entity.Note;
import online.onedaynote.api.services.interfaces.NotificationService;
import online.onedaynote.api.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Email Notification Service
 */
@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    @Value("${spring.mail.username}")
    private String from;

    private static final String MAIL_SUBJECT = "OneDayNote delivery report";
    private static final String MAIL_BODY = "Note was opened at ";
    private final JavaMailSender emailSender;

    public NotificationServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    /**
     * Sending letter
     */
    public void send(Note note) {

        log.info("Try send notify to email");
        if (Objects.isNull(note.notifyEmail)) return;
        try {

            MimeMessage message = emailSender.createMimeMessage();

            message.setSubject(MAIL_SUBJECT);

            message.setHeader("Content-Type", "text/plain; charset=UTF-8");
            message.setFrom(from);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(note.notifyEmail));
            message.setSubject(MAIL_SUBJECT, "utf-8");
            message.setContent(MAIL_BODY.concat(TimeUtils.now().format(
                    DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"))),
                    "text/html; charset=UTF-8");

            emailSender.send(message);
            log.info("Message was send");
        } catch (MessagingException ex) {

            log.error("Message wasn't send: " + ex.getMessage());
            //todo продумать повторную попытку отправки или добавление в еще одну таблицу для  джоббера
        }
    }
}
