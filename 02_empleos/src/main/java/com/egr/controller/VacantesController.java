package com.egr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@RequestMapping("/vacantes")
public class VacantesController {
	
	@GetMapping("/delete")
	public String elimiarVacante(@RequestParam("id") int idVacante, Model model) {
		System.out.println("Borrando vacante con Id " + idVacante);			
		model.addAttribute("id",idVacante);
		
		return "mensaje";
	}

	@GetMapping("/view/{id}")
	public String verDetalle(@PathVariable("id") int idVacante, Model model) {
		System.out.println("IdVacante " + idVacante);
		model.addAttribute("idVacante", idVacante);
		
		// Buscar los detalles de la vacante en la BBDD...
		
		return "vacantes/detalle";
	}
	
	
	

}
