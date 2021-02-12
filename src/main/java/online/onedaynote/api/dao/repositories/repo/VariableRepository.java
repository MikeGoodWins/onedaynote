package online.onedaynote.api.dao.repositories.repo;

import java.util.List;
import online.onedaynote.api.dao.entity.Variable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariableRepository extends JpaRepository<Variable, Long> {

    Variable findByKeyAndSimpleDate(String key, String created);

    List<Variable> findAllBySimpleDateBefore(String date);
}
