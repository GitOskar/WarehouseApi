package pl.warehouseapi.domain.primitive;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public boolean isNew() {
        return id == null;
    }
}