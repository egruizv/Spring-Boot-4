package com.egr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.egr.model.Vacante;

public interface VacantesRepository extends JpaRepository<Vacante, Integer> {
	
	// select * from Vacantes where estatus = ?
	List<Vacante> findByEstatus(String estatus);
	
	// select * from Vacantes where destacado = ?
	List<Vacante> findByDestacado(int destacado);
	
	// select * from Vacantes where estatus = ? and destacado = ?
	List<Vacante> findByDestacadoAndEstatusOrderByIdDesc(int destacado,String estatus);
	
	// select * from Vacantes where salario between ? and ?
	List<Vacante> findBySalarioBetweenOrderBySalarioDesc(double salario1,double salario2);
	
	// select * from Vacantes where salario estarus in(?,?,..)
	List<Vacante> findByEstatusIn(List<String> estatus);
}
