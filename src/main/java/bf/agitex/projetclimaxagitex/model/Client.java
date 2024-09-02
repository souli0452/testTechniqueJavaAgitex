package bf.agitex.projetclimaxagitex.model;

import bf.agitex.projetclimaxagitex.abstracClass.AbstractId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Client extends AbstractId {

  private String nom;
  private String prenom;
  private int age;
  private String profession;
  private double salaire;


}
