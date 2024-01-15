package br.com.iftech.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import br.com.iftech.repositories.EmployeeRepository;
import br.com.iftech.repositories.RecordRepository;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private RecordRepository recordRepoisotory;
	
	public byte[] generateReport(Long id) throws JRException, FileNotFoundException {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("empregador_id", id);

		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(employeeRepository.filterEmployee(id));
		JasperReport compileReport = JasperCompileManager
				.compileReport(new FileInputStream("src/main/resources/funcionario.jrxml"));

		JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, parameters,beanCollectionDataSource);

		// JasperExportManager.exportReportToPdfFile(jasperPrint,
		// System.currentTimeMillis() + ".pdf");

		byte data[] = JasperExportManager.exportReportToPdf(jasperPrint);


		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

		return data;

		
	}
	
	public byte[] generateReportHoras(Long id) throws JRException, FileNotFoundException {
		Map<String, Object> parameters = new HashMap<>();

		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(recordRepoisotory.listarFiltroMensal(id));
		JasperReport compileReport = JasperCompileManager
				.compileReport(new FileInputStream("src/main/resources/funcionarioHoras.jrxml"));

		JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, parameters,beanCollectionDataSource);

		// JasperExportManager.exportReportToPdfFile(jasperPrint,
		// System.currentTimeMillis() + ".pdf");

		byte data[] = JasperExportManager.exportReportToPdf(jasperPrint);


		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

		return data;

		
	}
	
	public byte[] generateReportRegistros(Long id) throws JRException, FileNotFoundException {
		Map<String, Object> parameters = new HashMap<>();

		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(recordRepoisotory.litsarRegistroDeTrabalho(id));
		JasperReport compileReport = JasperCompileManager
				.compileReport(new FileInputStream("src/main/resources/registro.jrxml"));
		
		parameters.put("empregado_id", id);

		JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, parameters,beanCollectionDataSource);

		// JasperExportManager.exportReportToPdfFile(jasperPrint,
		// System.currentTimeMillis() + ".pdf");

		byte data[] = JasperExportManager.exportReportToPdf(jasperPrint);


		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

		return data;

		
	}

}
