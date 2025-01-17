package bf.agitex.projetclimaxagitex.repository;

import bf.agitex.projetclimaxagitex.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
  List<Client> findByProfession(String profession);

}
