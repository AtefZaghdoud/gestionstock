package com.Atef.gestionstock.model;

import java.io.Serializable;
import java.sql.Date;
import java.time.Instant;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id ;
	
	@CreatedDate
	@Column(name ="creationDate", nullable = false, updatable = false )
	private Instant creationDate ;
	
	@LastModifiedDate
	@Column(name ="lastModifiedDate" )
	private Instant lastUpdateDate;

}
