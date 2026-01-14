package com.egr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

import com.egr.model.Categoria;

/**
 * TODO Diferencias entre CrudRepository, PagingAndSortingRepository y JpaRepository
 * Resumen rápido
  	CrudRepository: operaciones CRUD mínimas.
	PagingAndSortingRepository: Crud + findAll(Pageable) y findAll(Sort).
	JpaRepository: lo anterior + utilidades JPA (por ejemplo flush(), saveAllAndFlush, getOne/getReferenceById, deleteInBatch, etc.), y variantes convenientes.

 */

public interface CategoriasRepository extends JpaRepository<Categoria, Integer> {

}
