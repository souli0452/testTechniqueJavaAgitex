package bf.agitex.projetclimaxagitex.service;

import bf.agitex.projetclimaxagitex.exception.FileProcessingException;
import bf.agitex.projetclimaxagitex.model.Client;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileProcessingService {
    void processFile(File file) throws FileProcessingException;

    String detectFileType(File file);

    List<Client> parseCsv(File file) throws IOException;

    List<Client> parseJson(File file) throws IOException;

    List<Client> parseXml(File file) throws ParserConfigurationException, SAXException, IOException;

    List<Client> parseTxt(File file) throws IOException;
}
