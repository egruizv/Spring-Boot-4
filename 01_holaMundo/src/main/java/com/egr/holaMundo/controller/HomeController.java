package com.egr.holaMundo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/") // TODO el metodo raiz de nuestra aplicacion
	public String inicio() {
		return "Hola Mundo";
	}

}
