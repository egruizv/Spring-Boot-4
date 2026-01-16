package com.egr.controller;

import java.text.SimpleDateFormat;
//import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.egr.model.Categoria;
import com.egr.model.Vacante;
import com.egr.service.ICategoriasService;
import com.egr.service.IVacantesService;
import com.egr.util.Utileria;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/vacantes")
public class VacantesController {
	
	@Value("${empleos.app.ruta.imagenes}")
	private String ruta;

    @SuppressWarnings("unused")
	private final HomeController homeController;
	
	@Autowired
	private IVacantesService serviceVacantes;
	
	@Autowired
	private ICategoriasService serviceCategorias;

    VacantesController(HomeController homeController) {
        this.homeController = homeController;
    } // TODO variable para usar del service
	
	
    @GetMapping("/index")
    public String mostarIndex(Model model) {   
    	List<Vacante> listaVacantes = serviceVacantes.buscarTodas();    	
    	model.addAttribute("vacantes", listaVacantes);    	
    	return "vacantes/listVacantes";
    }
    
    
	@PostMapping("/save")
	public String guardar(Vacante vacante, BindingResult result, RedirectAttributes attributes, @RequestParam("archivoImagen") MultipartFile multiPart) {	//TODO BindingResult para recoger los errores
		
		if(result.hasErrors()) {
			for(ObjectError error : result.getAllErrors()) {
				System.err.println("Ocurrio un error : " + error.getDefaultMessage());				
			}
			return  "vacantes/formVacante";
		}
		
		// Inicio Subir Archivos
		if (!multiPart.isEmpty()) {
			//String ruta = "/empleos/img-vacantes/"; // Linux/MAC
			//String ruta = "c:/empleos/img-vacantes/"; // Windows
			String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
			if (nombreImagen != null){ // La imagen si se subio
				// Procesamos la variable nombreImagen
				vacante.setImagen(nombreImagen);
			}
		}
		// Fin Subir archivos
		
		
		// Con el id de categoria obtenemos el resto de datos para que se guarden correctamente
		Categoria categoriaAux = serviceCategorias.buscarPorId(vacante.getCategoria().getId());
		vacante.setCategoria(categoriaAux);
		serviceVacantes.guardar(vacante);
		
		//model.addAttribute("msg","Registro insertado correctamente");//TODO solo visible desde esta peticion, como hacemos redirect no se ve a no ser que hagamos algo
		//TODO para que sea visible en un redirect usamos RedirectAttributes
		attributes.addFlashAttribute("msg","Registro insertado correctamente");
		System.out.println("Vacante: " + vacante);
		//return "vacantes/listVacantes";
		//Hacemos un Redireccionamiento a /vacantes/index usando redirect:		
		return  "redirect:/vacantes/index"; //TODO redirect: --> Peticion GET
	}
	
	/* Usando @RequestParam lo comentamos , mejor usamos Data Binding
	@PostMapping("/save")
	public String guardar(@RequestParam("nombre") String nombre,
			@RequestParam("descripcion") String descripcion,
			@RequestParam("categoria") int categoria,	// TODO la incluimos nosotros		
			@RequestParam("estatus") String estatus,
			@RequestParam("fecha") String fechaPublicacion,	
			@RequestParam("destacado") int destacado,
			@RequestParam("salario") double salario,
			@RequestParam("archivoImagen") String archivoImagen, // TODO la incluimos nosotros		
			@RequestParam("detalles") String detalles
			
			) {
		
		System.out.println("Nombre Vacante: " + nombre);
		System.out.println("Descripcion: " + descripcion);
		System.out.println("Categoria: " + categoria);
		System.out.println("Estatus: " + estatus);
		System.out.println("Fecha de Pubicacion: " + fechaPublicacion);
		System.out.println("Destacado: " + destacado);
		System.out.println("Salario Pfrecido: " + salario);
		System.out.println("Archivo Imagen: " + archivoImagen);
		System.out.println("Detalles: " + detalles);
		return  "vacantes/listVacantes";
	}
	*/
	@GetMapping("/create")
	public String crear(Vacante vacante, Model model) {
		List<Categoria> listaCategorias = serviceCategorias.buscarTodas();
		model.addAttribute("categorias", listaCategorias);
		return  "vacantes/formVacante";
	}
	
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
	

	@InitBinder  //TODO InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	

}
