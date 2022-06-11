package com.Atef.gestionstock.service;

import java.util.List;

import com.Atef.gestionstock.dto.ClientDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static com.Atef.gestionstock.utils.Constants.APP_ROOT;

public interface ClientService {

	ClientDto save(ClientDto dto);

	ClientDto findById(Integer id);

	List<ClientDto> findAll();

	void delete(Integer id);

}
