package br.com.iftech.dtos.responses;

import java.sql.Timestamp;

public interface RegisterFilterResponse {

	Timestamp getHora_entrada();
	Timestamp getHora_saida();
}
