package online.onedaynote.api.dao.repositories.repo;

import online.onedaynote.api.dao.entity.Note;
import online.onedaynote.api.dao.repositories.interfaces.NoteRepository;
import online.onedaynote.api.utils.EncryptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class NoteRepositoryImpl implements NoteRepository {

    @Value("${spring.redis.hashKey}")
    private String hashKey;

    private final RedisTemplate<String, Note> redisTemplate;

    public NoteRepositoryImpl(
            RedisTemplate<String, Note> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void save(Note note) {
        redisTemplate.opsForHash().put(note.getKey(), hashKey, note);
    }

    @Override
    public Note findByKey(String key) {
        return (Note) redisTemplate.opsForHash().get(hashKey, key);
    }

    @Override
    public void delete(String key) {
        redisTemplate.opsForHash().delete(hashKey, key);
    }
}
