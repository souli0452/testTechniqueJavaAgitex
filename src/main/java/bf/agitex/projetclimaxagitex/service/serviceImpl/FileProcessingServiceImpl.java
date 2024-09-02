package bf.agitex.projetclimaxagitex.service.serviceImpl;

import bf.agitex.projetclimaxagitex.exception.FileProcessingException;
import bf.agitex.projetclimaxagitex.model.Client;
import bf.agitex.projetclimaxagitex.repository.ClientRepository;
import bf.agitex.projetclimaxagitex.service.FileProcessingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileProcessingServiceImpl implements FileProcessingService {

  private final ClientRepository clientRepository;


  public FileProcessingServiceImpl(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  @Override
  public void processFile(File file) throws FileProcessingException {
    String fileType = detectFileType(file);

    try {
      List<Client> clients = switch (fileType) {
        case "csv" -> parseCsv(file);
        case "json" -> parseJson(file);
        case "xml" -> parseXml(file);
        case "txt" -> parseTxt(file);
        default -> throw new FileProcessingException("Unsupported file type: " + fileType);
      };

      clients.forEach(clientRepository::save);
    } catch (IOException | ParserConfigurationException | SAXException e) {
      throw new FileProcessingException("Error processing file: " + file.getName(), e);
    }
  }

  @Override
  public String detectFileType(File file) {
    String fileName = file.getName().toLowerCase();
    if (fileName.endsWith(".csv")) {
      return "csv";
    } else if (fileName.endsWith(".json")) {
      return "json";
    } else if (fileName.endsWith(".xml")) {
      return "xml";
    } else if (fileName.endsWith(".txt")) {
      return "txt";
    } else {
      return "unknown";
    }
  }

  @Override
  public List<Client> parseCsv(File file) throws IOException {
    List<Client> clients = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] fields = line.split(",");
        if (fields.length == 5) {
          Client client = new Client();
          client.setNom(fields[0].trim());
          client.setPrenom(fields[1].trim());
          client.setAge(Integer.parseInt(fields[2].trim()));
          client.setProfession(fields[3].trim());
          client.setSalaire(Double.parseDouble(fields[4].trim()));
          clients.add(client);
        }
      }
    }
    return clients;
  }

  @Override
  public List<Client> parseJson(File file) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    List<Client> clients = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, Client.class));
    return clients;
  }

  @Override
  public List<Client> parseXml(File file) throws ParserConfigurationException, SAXException, IOException {
    List<Client> clients = new ArrayList<>();
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document document = builder.parse(file);

    var nodeList = document.getElementsByTagName("client");

    for (int i = 0; i < nodeList.getLength(); i++) {
      var node = nodeList.item(i);
      var element = (org.w3c.dom.Element) node;

      Client client = new Client();
      client.setNom(element.getElementsByTagName("nom").item(0).getTextContent());
      client.setPrenom(element.getElementsByTagName("prenom").item(0).getTextContent());
      client.setAge(Integer.parseInt(element.getElementsByTagName("age").item(0).getTextContent()));
      client.setProfession(element.getElementsByTagName("profession").item(0).getTextContent());
      client.setSalaire(Double.parseDouble(element.getElementsByTagName("salaire").item(0).getTextContent()));

      clients.add(client);
    }

    return clients;
  }

  @Override
  public List<Client> parseTxt(File file) throws IOException {
    // Suppose that the TXT format is similar to CSV, but separated by spaces instead of commas
    List<Client> clients = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] fields = line.split("\\s+");
        if (fields.length == 5) {
          Client client = new Client();
          client.setNom(fields[0].trim());
          client.setPrenom(fields[1].trim());
          client.setAge(Integer.parseInt(fields[2].trim()));
          client.setProfession(fields[3].trim());
          client.setSalaire(Double.parseDouble(fields[4].trim()));
          clients.add(client);
        }
      }
    }
    return clients;
  }
}
