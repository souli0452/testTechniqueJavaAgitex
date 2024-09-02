package bf.agitex.projetclimaxagitex.controller;
import bf.agitex.projetclimaxagitex.exception.FileProcessingException;
import bf.agitex.projetclimaxagitex.service.serviceImpl.FileProcessingServiceImpl;
import bf.agitex.projetclimaxagitex.service.serviceImpl.ReportingServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import static bf.agitex.projetclimaxagitex.constant.ClientUrlsApi.*;

@RestController
@RequestMapping(ROOT_API)
public class ReportController {

  private final FileProcessingServiceImpl fileProcessingService;
  private final ReportingServiceImpl reportingService;

  public ReportController(FileProcessingServiceImpl fileProcessingService, ReportingServiceImpl reportingService) {
    this.fileProcessingService = fileProcessingService;
    this.reportingService = reportingService;
  }

  @PostMapping(UPLOAD_FILE)
  public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
    try {
      File tempFile = File.createTempFile("upload-", file.getOriginalFilename());
      file.transferTo(tempFile);
      fileProcessingService.processFile(tempFile);
      return ResponseEntity.ok("File processed successfully");
    } catch (IOException | FileProcessingException e) {
      return ResponseEntity.status(500).body("Error processing file: " + e.getMessage());
    }
  }

  @GetMapping(AVERAGE_SALARIES)
  public ResponseEntity<Map<String, Double>> getAverageSalariesByProfession() {
    Map<String, Double> averages = reportingService.calculateAverageSalaryByProfession();
    return ResponseEntity.ok(averages);
  }
}
