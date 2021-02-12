package online.onedaynote.api.dao.repositories.repo;

import online.onedaynote.api.dao.entity.VariableHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariableHistoryRepository extends JpaRepository<VariableHistory, Long> {

    VariableHistory findByKeyAndCreated(String key, String created);


}
