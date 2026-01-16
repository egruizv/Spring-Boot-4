package com.egr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.egr.model.Categoria;
import com.egr.service.ICategoriasService;

@Controller
@RequestMapping(value = "/categorias") // TODO A nivel de Clase para url sea http://localhost:8080/categorias/***
public class CategoriaController {
	
	@Autowired
	private ICategoriasService serviceCategorias;
	
	

	@GetMapping("/index") // http://localhost:8080/categorias/index
	//@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String mostrarIndex(Model model) {
		List<Categoria> listaCategorias = serviceCategorias.buscarTodas();
		model.addAttribute("categorias", listaCategorias);
		return "categorias/listCategorias";
	}

	@GetMapping("/create") //http://localhost:8080/categorias/create
	//@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String crear(Categoria categoria) {
		return "categorias/formCategoria";
	}

	@PostMapping("/save") //http://localhost:8080/categorias/save
	//@RequestMapping(value="/save", method=RequestMethod.POST)
	public String guardar(Categoria categoria, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			for(ObjectError error : result.getAllErrors()) {
				System.err.println("Ocurrio un error : " + error.getDefaultMessage());				
			}
			return  "categorias/formCategoria";
		}
		
		System.out.println("Categoria: " + categoria);		
		
		// Guadamos el objeto categoria en la bd
		serviceCategorias.guardar(categoria);
		attributes.addFlashAttribute("msg", "Los datos de la categoría fueron guardados!");	
		return "redirect:/categorias/index";
	}
}
