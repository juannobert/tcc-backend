package br.com.iftech.controllers;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.iftech.services.ReportService;
import net.sf.jasperreports.engine.JRException;

@Controller
@RequestMapping("/relatorios")
public class ReportController {
	
	@Autowired
	private ReportService service;
	
	@GetMapping("/funcionario/{id}")
	public ResponseEntity<byte[]> relatorioFuncionarios(@PathVariable Long id) throws FileNotFoundException, JRException{
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(service.generateReport(id));
	}
	
	@GetMapping("/funcionario/horas/{id}")
	public ResponseEntity<byte[]> relatorioFuncionarioHoras(@PathVariable Long id) throws FileNotFoundException, JRException{
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(service.generateReportHoras(id));
	}

}
