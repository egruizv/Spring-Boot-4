package com.egr.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.egr.model.Vacante;
import com.egr.service.IVacantesService;

@Controller
public class HomeController {
	
	@Autowired
	private IVacantesService serviceVacantes; // TODO variable para usar del service
	
	@GetMapping("/tabla")
	public String mostrarTabla(Model model) {		
		List<Vacante> listaVacantes = serviceVacantes.buscartodas();
		model.addAttribute("vacantes", listaVacantes);
		
		return "tabla";
	}
	
	@GetMapping("/detalle")
	public String mostrarDetalle(Model model) {
		Vacante vacante = new Vacante();
		vacante.setNombre("ingeniero de comunicaciones");
		vacante.setDescripcion("Se solicitya ingeniero para dar soporte a internet");
		vacante.setFecha(new Date());
		vacante.setSalario(9700.0);
		model.addAttribute("vacante",vacante);
		return "detalle";
	}
	
	@GetMapping("/listado")
	public String mostrarListado(Model model) {
		List<String> lista = new LinkedList<String>();
		lista.add("Ingeriero de Sistemas");
		lista.add("Auxiliar de Contabilidad");
		lista.add("Vendedor");
		lista.add("Arquitecto");
		model.addAttribute("empleos", lista);
		return "listado";
	}
	
	
	@GetMapping("/") //TODO Peticion tipo Get en Raiz (http://localhost:8080/)
	public String mostrarHome(Model model) {
		List<Vacante> listaVacantes = serviceVacantes.buscartodas();
		model.addAttribute("vacantes", listaVacantes);		
		return "home"; //TODO Esta es la vista, que corresponde a la pagina html que hay en src/main/resource/templates/home.html
	}
	
}
