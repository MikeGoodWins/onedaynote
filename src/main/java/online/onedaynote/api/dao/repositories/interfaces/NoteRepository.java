package online.onedaynote.api.dao.repositories.interfaces;

import online.onedaynote.api.dao.entity.Note;

public interface NoteRepository {

    void save(Note note);

    Note findByKey(String key);

    void delete (String key);
}
