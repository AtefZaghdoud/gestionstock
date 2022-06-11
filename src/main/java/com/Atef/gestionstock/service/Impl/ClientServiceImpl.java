package com.Atef.gestionstock.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Atef.gestionstock.dto.ClientDto;
import com.Atef.gestionstock.exception.EntityNotFoundException;
import com.Atef.gestionstock.exception.ErrorCodes;
import com.Atef.gestionstock.exception.InvalidEntityException;
import com.Atef.gestionstock.repository.ClientRepository;
import com.Atef.gestionstock.service.ClientService;
import com.Atef.gestionstock.validator.ClientValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

	private ClientRepository clientRepository;

	@Autowired
	public ClientServiceImpl(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public ClientDto save(ClientDto dto) {
		List<String> errors = ClientValidator.validate(dto);
		if (!errors.isEmpty()) {
			log.error("Client is not Valid {}", dto);
			throw new InvalidEntityException("Le client n'est pas valide", ErrorCodes.CLIENT_NOT_VALID, errors);

		}

		return ClientDto.fromEntity(clientRepository.save(ClientDto.toEntity(dto)));
	}

	@Override
	public ClientDto findById(Integer id) {
		if (id == null) {
			log.error("Client id is null");
			return null;
		}

		return clientRepository.findById(id).map(ClientDto::fromEntity).orElseThrow(() -> new EntityNotFoundException(
				"Aucun client avec l'ID " + id + " n'ete trouve dans la BDD ", ErrorCodes.CLIENT_NOT_FOUND));
	}

	@Override
	public List<ClientDto> findAll() {
		return clientRepository.findAll().stream().map(ClientDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		if (id == null) {
			log.error("Client id is null");
			return;
		}
		clientRepository.deleteById(id);

	}

}
