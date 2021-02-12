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

@Getter
@Setter
@Entity
@Table(name = "variable_history")
@NoArgsConstructor
public class VariableHistory implements Serializable {

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

    public VariableHistory(Variable variable) {
        this.key = variable.getKey();
        this.value = variable.getValue();
        this.simpleDate = variable.getSimpleDate();
        this.created = variable.getCreated();
    }
}
