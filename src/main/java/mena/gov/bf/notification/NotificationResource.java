package mena.gov.bf.notification;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class NotificationResource {
    private final NotificationService notificationService;

    public NotificationResource(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/mails/send-alerte")
    public void sendAlerteMailOnChedulMethode() {
        this.notificationService.sendAlerteOfDelaisExecution();
    }
}
