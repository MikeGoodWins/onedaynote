package online.onedaynote.api.dao.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import online.onedaynote.api.dto.enums.NoteType;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "notes_history")
public class NoteHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "key")
    public String key;

    @Column(name = "payload")
    public String payload;

    @Column(name = "extra_payload")
    public String extraPayload;

    @Column(name = "note_type")
    public int noteType;

    @Column(name = "removable")
    public boolean removable;

    @Column(name = "need_notify")
    public boolean needNotify;

    @Column(name = "notified")
    public boolean notified;

    @Column(name = "notify_email")
    public String notifyEmail;

    @Column(name = "simple_date")
    public String simpleDate;

    @Column(name = "created")
    public LocalDateTime created;

    public NoteHistory(Note note) {
        this.key = note.getKey();
        this.payload = note.getPayload();
        this.noteType = note.getNoteType();
        this.removable = note.isRemovable();
        this.needNotify = note.isNeedNotify();
        this.notifyEmail = note.getNotifyEmail();
        this.simpleDate = note.getSimpleDate();
        this.created = note.getCreated();
    }
}
