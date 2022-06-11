package com.Atef.gestionstock.dto;

import java.util.List;

import com.Atef.gestionstock.model.Client;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ClientDto {

	private Integer id ;

	private String nom ;
	
	private String prenom;
	
	private AdresseDto addresse;
	
	private Integer idEntreprise;
	
	private String photo ;
	
	private String mail;
	
	private String numTel;
	
	@JsonIgnore
	private List<CommandeClientDto> commandeClients ;
	
	public static ClientDto fromEntity(Client client) {
		
		if (client ==null) {
			return null;
		}
		return ClientDto.builder()
				.id(client.getId())
				.nom(client.getNom())
				.prenom(client.getPrenom())
				.mail(client.getMail())
				.numTel(client.getNumTel())
				.photo(client.getPhoto())
				.idEntreprise(client.getIdEntreprise())
				.addresse(AdresseDto.fromEntity(client.getAddresse()))
				//.commandeClients(client.getCommandeClients().stream().map(e -> "s").collect(Collectors.toList()))
				.build();
		
	}
	
	public static Client toEntity(ClientDto clientDto) {
		if (clientDto == null ) {
			return null ;
		}
		Client client = new Client();
		client.setId(clientDto.getId());
		client.setNom(clientDto.getNom());
		client.setPrenom(clientDto.getPrenom());
		client.setMail(clientDto.getMail());
		client.setNumTel(clientDto.getNumTel());
		client.setPhoto(clientDto.getPhoto());
		client.setAddresse(AdresseDto.toEntity(clientDto.getAddresse()));
		client.setIdEntreprise(clientDto.getIdEntreprise());
		return client ;
	}
	
	
}
