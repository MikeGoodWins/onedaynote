package online.onedaynote.api.dao.repositories.repo;

import java.util.Optional;
import online.onedaynote.api.dao.entity.NoteHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteHistoryRepository extends JpaRepository<NoteHistory, Long> {

    Optional<NoteHistory> findByKey(String key);
}
