package com.egr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egr.model.Vacante;
import com.egr.service.IVacantesService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@RequestMapping("/vacantes")
public class VacantesController {
	
	@Autowired
	private IVacantesService serviceVacantes; // TODO variable para usar del service
	
	@GetMapping("/delete")
	public String elimiarVacante(@RequestParam("id") int idVacante, Model model) {
		System.out.println("Borrando vacante con Id " + idVacante);			
		model.addAttribute("id",idVacante);
		
		return "mensaje";
	}

	@GetMapping("/view/{id}")
	public String verDetalle(@PathVariable("id") int idVacante, Model model) {
		
		Vacante vacante = serviceVacantes.buscarPorId(idVacante);
		
		System.out.println("vacante " + vacante);		
		model.addAttribute("vacante", vacante);
		
		
		// Buscar los detalles de la vacante en la BBDD...
		
		return "detalle";
	}
	
	
	

}
