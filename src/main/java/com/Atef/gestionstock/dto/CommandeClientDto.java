package com.Atef.gestionstock.dto;

import java.time.Instant;
import java.util.List;

import com.Atef.gestionstock.model.CommandeClient;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommandeClientDto {

	private Integer id ;

	private String code ;
	
	private Instant dateCommande ;
	
	private Integer idEntreprise;

	private ClientDto client ;
	
	private List<LigneCommandeClientDto> ligneCommandeClients ;
	
	public static CommandeClientDto fromEntity(CommandeClient commandeClient) {
		
		if (commandeClient==null) {
			return null;
		}
		return CommandeClientDto.builder()
				.id(commandeClient.getId())
				.code(commandeClient.getCode())
				.dateCommande(commandeClient.getDateCommande())
				.client(ClientDto.fromEntity(commandeClient.getClient()))
				.idEntreprise(commandeClient.getIdEntreprise())
				//.ligneCommandeClients(LigneCommandeClientDto.fromEntity(commandeClient.getLigneCommandeClients()))
				.build();
		
	}
	
	public static CommandeClient toEntity(CommandeClientDto commandeClientDto) {
		if (commandeClientDto == null ) {
			return null ;
		}
		CommandeClient commandeClient = new CommandeClient();
		commandeClient.setId(commandeClientDto.getId());
		commandeClient.setClient(ClientDto.toEntity(commandeClientDto.getClient()));
		commandeClient.setDateCommande(commandeClientDto.getDateCommande());
		commandeClient.setIdEntreprise(commandeClientDto.getIdEntreprise());
		commandeClient.setCode(commandeClientDto.getCode());
		//commandeClient.setLigneCommandeClients(null);
		
		
		return commandeClient;
	}
	
	
	
}
