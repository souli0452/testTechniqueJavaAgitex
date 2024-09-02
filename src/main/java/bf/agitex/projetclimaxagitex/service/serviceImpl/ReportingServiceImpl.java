package bf.agitex.projetclimaxagitex.service.serviceImpl;
import bf.agitex.projetclimaxagitex.repository.ClientRepository;
import bf.agitex.projetclimaxagitex.service.ReportingService;
import org.springframework.stereotype.Service;
import bf.agitex.projetclimaxagitex.model.Client;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportingServiceImpl implements ReportingService {

  private final ClientRepository clientRepository;


  public ReportingServiceImpl(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  @Override
  public Map<String, Double> calculateAverageSalaryByProfession() {
    return clientRepository.findAll().stream()
      .collect(Collectors.groupingBy(
        Client::getProfession,
        Collectors.averagingDouble(Client::getSalaire)
      ));
  }
}
