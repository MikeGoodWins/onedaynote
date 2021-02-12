package online.onedaynote.api.dao.repositories.repo;

import java.util.List;
import java.util.Optional;
import online.onedaynote.api.dao.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    Optional<Note> findByKey(String key);

    List<Note> findAllBySimpleDateBefore(String date);
}
