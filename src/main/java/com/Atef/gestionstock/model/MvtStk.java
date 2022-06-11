package com.Atef.gestionstock.model;

import java.math.BigDecimal;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="mvtstk")
public class MvtStk extends AbstractEntity {
	
	@Column(name="datemvt")
	private Instant dateMvt;
	
	@Column(name="quantite")
	private BigDecimal quantite;	
	
	@Column(name="identreprise")
	private Integer idEntreprise;
	
	@ManyToOne
	@JoinColumn(name ="idarticle")
	private Article article ;
	
	@Column(name ="typemvt")
	private TypeMvtStk typeMvt;
	

}
