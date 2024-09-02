package bf.agitex.projetclimaxagitex.abstracClass;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.util.UUID;

@MappedSuperclass
public class AbstractId {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    @JsonIgnore
    protected UUID id;
}
