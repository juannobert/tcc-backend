package br.com.iftech.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.iftech.dtos.requests.RecordRequest;
import br.com.iftech.dtos.responses.MonthFilterResponse;
import br.com.iftech.dtos.responses.RecordResponse;
import br.com.iftech.dtos.responses.WorkingDaysFilterResponse;
import br.com.iftech.services.RecordService;

@RestController
@RequestMapping("/registros")
public class RecordController {

	@Autowired
	private RecordService service;
	
	
	@PostMapping("/registrar/{id}")
	public RecordResponse registrar(@PathVariable Long id,@RequestBody RecordRequest request) {
		return service.registrar(request,id);
	}
	
	@GetMapping("/listar/{userId}")
	public Page<RecordResponse> listar(
			@PathVariable Long userId,
			@RequestParam(name = "mes",defaultValue = "0") Integer mes,
			Pageable pageable){
		return service.listar(userId, pageable,mes);
	}
	
	
	@GetMapping("/diario/{id}")
	public RecordResponse registroDiario(@PathVariable Long id) {
		return service.buscarRegistroDiario(id);
	}
	
	@GetMapping("/filtro/mensal/{id}")
	public List<MonthFilterResponse> filtroMensal(@PathVariable Long id){
		return service.filtroMensal(id);
	}
	@GetMapping("/filtro/diario/{id}")
	public List<WorkingDaysFilterResponse> filtroDiario(@PathVariable Long id){
		return service.filtroDiario(id);
	}
	
}
