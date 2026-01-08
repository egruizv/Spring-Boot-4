package com.egr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/categorias") // TODO A nivel de Clase para url sea http://localhost:8080/categorias/***
public class CategoriaController {

	@GetMapping("/index") // http://localhost:8080/categorias/index
	//@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String mostrarIndex(Model model) {
		return "categorias/listCategorias";
	}

	@GetMapping("/create") //http://localhost:8080/categorias/create
	//@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String crear() {
		return "categorias/formCategoria";
	}

	@PostMapping("/save") //http://localhost:8080/categorias/save
	//@RequestMapping(value="/save", method=RequestMethod.POST)
	public String guardar(@RequestParam("nombre") String nombre, @RequestParam("descripcion") String descripcion) {
		
		System.out.println("Categoria: " + nombre);
		System.out.println("Descripcion: " + descripcion);
		
		// Aqui se graban en BBDD
		
		return "categorias/listCategorias";
	}
}
