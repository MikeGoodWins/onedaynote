package online.onedaynote.api.dao.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import online.onedaynote.api.dto.note.NoteCreate;
import online.onedaynote.api.utils.TimeUtils;

@Getter
@Setter
@Entity
@Table(name = "notes")
public class Note implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "key")
    public String key;

    @Column(name = "payload")
    public String payload;

    @Column(name = "note_type")
    public int noteType;

    @Column(name = "removable")
    public boolean removable;

    @Column(name = "need_notify")
    public boolean needNotify;

    @Column(name = "notify_email")
    public String notifyEmail;

    @Column(name = "simple_date")
    public String simpleDate;

    @Column(name = "created")
    public LocalDateTime created;

    public Note(String encodeKey, String encodePayload, NoteCreate model) {
        this.key = encodeKey;
        this.payload = encodePayload;
        this.noteType = model.getType().getType();
        this.removable = model.isRemovable();
        this.needNotify = model.isNeedNotify();
        this.notifyEmail = ((model.isNeedNotify()) ? model.getNotifyEmail() : "");
        this.simpleDate = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(TimeUtils.now());
        this.created = TimeUtils.now();
    }

    public Note() {

    }
}
