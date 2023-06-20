package br.com.iftech.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.iftech.dtos.requests.RecordRequest;
import br.com.iftech.dtos.responses.RecordResponse;
import br.com.iftech.services.RecordService;

@RestController
@RequestMapping("/registros")
public class RecordController {

	
	@Autowired
	private RecordService service;
	
	@GetMapping("/buscar")
	public RecordResponse registrar(@RequestBody RecordRequest request) {
		return service.registrar(request);
	}
}
