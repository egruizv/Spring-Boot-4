package com.egr.service;

import java.util.List;
import com.egr.model.Vacante;

public interface IVacantesService {

	List<Vacante> buscarTodas();	
	Vacante buscarPorId(Integer idVacante);
	void guardar(Vacante vacante);
}
