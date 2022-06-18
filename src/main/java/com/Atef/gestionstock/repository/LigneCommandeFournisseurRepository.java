package com.Atef.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Atef.gestionstock.model.LigneCommandeFournisseur;

import java.util.List;

public interface LigneCommandeFournisseurRepository extends JpaRepository<LigneCommandeFournisseur, Integer> {

    List<LigneCommandeFournisseur> findAllByArticleId(Integer idCommande);

    List<LigneCommandeFournisseur> findAllByCommandeFournisseurId(Integer idCommande);
}
