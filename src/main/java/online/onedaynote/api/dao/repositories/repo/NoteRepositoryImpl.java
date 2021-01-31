package online.onedaynote.api.dao.repositories.repo;

import java.util.Optional;
import online.onedaynote.api.dao.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepositoryImpl extends JpaRepository<Note, Long> {

    Optional<Note> findByKey(String key);


}
