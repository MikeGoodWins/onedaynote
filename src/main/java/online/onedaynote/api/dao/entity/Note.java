package online.onedaynote.api.dao.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import online.onedaynote.api.dto.enums.NoteType;
import online.onedaynote.api.dto.note.NoteCreate;
import online.onedaynote.api.dto.note.NoteDto;
import online.onedaynote.api.utils.EncryptionUtils;
import online.onedaynote.api.utils.StringUtils;
import online.onedaynote.api.utils.TimeUtils;

@Getter
@Setter
public class Note implements Serializable {

    public String key;

    public String payload;

    public NoteType noteType;

    public boolean removable;

    public boolean needNotify;

    public String notifyEmail;

    public LocalDateTime created;

    public Note(String key, String payload, NoteType noteType, boolean removable,
                boolean needNotify, String notifyEmail) {
        this.key = EncryptionUtils.encrypt(key);
        this.payload = payload;
        this.noteType = noteType;
        this.removable = removable;
        this.needNotify = needNotify;
        this.notifyEmail = ((needNotify) ? notifyEmail : "");
        this.created = TimeUtils.now();
    }

    public Note(NoteCreate model, String key) {
        this.key = EncryptionUtils.encrypt(key);
        this.payload = (StringUtils.isNullOrEmpty(model.getText())
                ? model.getPicture() : model.getText());
        this.noteType = model.getType();
        this.removable = model.isRemovable();
        this.needNotify = model.isNeedNotify();
        this.notifyEmail = ((model.isNeedNotify()) ? model.getNotifyEmail() : "");
        this.created = TimeUtils.now();
    }

    public NoteDto toDto(){
        return new NoteDto(this.noteType, EncryptionUtils.decrypt(this.key, this.payload));
    }
}
