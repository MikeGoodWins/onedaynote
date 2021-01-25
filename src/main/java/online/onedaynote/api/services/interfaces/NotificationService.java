package online.onedaynote.api.services.interfaces;

import online.onedaynote.api.dao.entity.Note;

public interface NotificationService {

    public void send(Note note);
}
