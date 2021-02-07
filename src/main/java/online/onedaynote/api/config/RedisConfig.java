//package online.onedaynote.api.config;
//
//import online.onedaynote.api.dao.entity.Note;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//
//@Configuration
//public class RedisConfig extends CachingConfigurerSupport {

//    @Value("${spring.redis.host}")
//    private String redisServer;
//
//    @Value("${spring.redis.port}")
//    private int redisPort;

//    @Value("${spring.redis.username}")
//    private String username;
//
//    @Value("${spring.redis.password}")
//    private String password;
//
//    @Bean
//    public JedisConnectionFactory redisConnectionFactory() {
//
//        RedisStandaloneConfiguration config =
//                new RedisStandaloneConfiguration(redisServer, redisPort);
//        config.setUsername(username);
//        config.setPassword(password);
//        return new JedisConnectionFactory(config);
//    }

//    @Bean
//    RedisTemplate<String, Note> redisTemplate() {
//        RedisTemplate<String, Note> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(redisConnectionFactory());
//        return redisTemplate;
//    }
//}
