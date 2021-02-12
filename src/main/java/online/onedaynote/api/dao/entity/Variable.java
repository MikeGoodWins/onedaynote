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
import lombok.NoArgsConstructor;
import lombok.Setter;
import online.onedaynote.api.utils.TimeUtils;

@Getter
@Setter
@Entity
@Table(name = "variables")
@NoArgsConstructor
public class Variable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "key")
    public String key;

    @Column(name = "value")
    public String value;

    @Column(name = "simple_date")
    public String simpleDate;

    @Column(name = "created")
    public LocalDateTime created;

    public Variable(String key, String value) {
        this.key = key;
        this.value = value;
        this.simpleDate = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(TimeUtils.now());
        this.created = TimeUtils.now();
    }
}
