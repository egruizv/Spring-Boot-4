package com.egr.service;

import java.util.List;
import com.egr.model.Vacante;

public interface IVacantesService {

	List<Vacante> buscartodas();	
	Vacante buscarPorId(Integer idVacante);
}
