package com.egr;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.egr.model.Categoria;
import com.egr.repository.CategoriasRepository;

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner { // TODO para que sirva de Console Application usamos la interface CommandLineRunner

	@Autowired
	private CategoriasRepository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Codigo que se ejecuta cuando se arranca por ser  implements CommandLineRunner
		System.out.println("Ejemplos de Spring Data JPA");	
		//guardar();
		//buscarPorId(17);
		//modificar(17);
		//eliminar(19);
		//System.out.println(repo);	
		//contar();
		// eliminarTodos(); // TODO No funciona si tiene FK con otras tablas !!!
		/*
		List<Integer> ids = new LinkedList<Integer>();
		ids.add(1);
		ids.add(4);
		ids.add(10);
		encontrarPorIds(ids);
		*/
		//buscarTodos();
		//existeId(50);	
		/*
		List<Categoria> listaCategorias = getListaCategorias();
		saveAll(listaCategorias);
		*/
		//borrarTodosEnBloque();
		//buscarTodosOrdenados();
		//buscarTodosPaginacion();
		buscarTodosPaginacionOrdenados();
		
	}
	
	// TODO Estamos usando desde el principio JpaRepositoru 
	
	private void buscarTodosPaginacionOrdenados() {
	
		// Paginacion + Ordenacion 
	Page<Categoria> pageCategorias = repo.findAll(PageRequest.of(0, 5,Sort.by("nombre")));
	
	//TODO podemos preguntar usando el objeto pageCategorias el numero de paginas y el numero de registros
	System.out.println("TOTAL REGISTROS: "  + pageCategorias.getTotalElements());
	System.out.println("TOTAL PAGINAS: "  + pageCategorias.getTotalPages());
	
	for (Categoria cat : pageCategorias.getContent()) {
		System.out.println(cat.getId() + " " + cat.getNombre());
	}
	}
	
	
	private void buscarTodosPaginacion() {
		/*
		  	pageNumber zero-based page number, must not be negative.--> Numero de pagina Nota la pagina 1 es el 0
			pageSize the size of the page to be returned, must be greater than 0.--> Numero de elementos por pagina. tiene q	ue ser positivo
		 */
		Page<Categoria> pageCategorias = repo.findAll(PageRequest.of(0, 3));
		//QUERYS
		//Hibernate: select c1_0.id,c1_0.descripcion,c1_0.nombre from categorias c1_0 limit ?,?
		//Hibernate: select count(c1_0.id) from categorias c1_0
		
		//TODO podemos preguntar usando el objeto pageCategorias el numero de paginas y el numero de registros
		System.out.println("TOTAL REGISTROS: "  + pageCategorias.getTotalElements());
		System.out.println("TOTAL PAGINAS: "  + pageCategorias.getTotalPages());
		
		for (Categoria cat : pageCategorias.getContent()) {
			System.out.println(cat.getId() + " " + cat.getNombre());
		}
	}
	
	private void buscarTodosOrdenados() {
		//List<Categoria> listCategorias = repo.findAll(Sort.by("nombre"));// Si no ponemos nada es ascendiente	
		List<Categoria> listCategorias = repo.findAll(Sort.by("nombre").descending());//Descenciente 	
		// select c1_0.id,c1_0.descripcion,c1_0.nombre from categorias c1_0 order by c1_0.nombre
		for (Categoria cat : listCategorias) {
			System.out.println(cat.getId() + " " + cat.getNombre());
		}
	}
	
	
	private void borrarTodosEnBloque() { //TODO Usar con precaucion ya que borra todos en bloque (Deletes all entities in a batch call.)
		repo.deleteAllInBatch();// delete c1_0 from categorias c1_0
	}
	
	
	private void saveAll(List<Categoria> categorias) {
		repo.saveAll(categorias);
	}
	
	private void existeId(Integer id) {
		boolean bExiste = repo.existsById(id);
		System.out.println("¿La categoria con ID: " + id +" existe? :" + bExiste);
		
	}
	
	private void buscarTodos() {
		List<Categoria> listCategorias = repo.findAll();		
		for (Categoria cat : listCategorias) {
			System.out.println(cat);
			System.out.println(cat.getId() + " " + cat.getNombre());
		}
		
	}
	
	
	
	private void encontrarPorIds(List<Integer> ids) {
		List<Categoria> categorias = repo.findAllById(ids);
		for (Categoria cat : categorias) {
			System.out.println(cat);
		}
	}
	
	private void eliminarTodos() { //TODO cuando hay pocos registros ya que elimina uno a uno
		repo.deleteAll();
	}
	
	private void contar() {
		long count = repo.count();
		System.out.println("Total categorias :" + count);
	}
	
	private void modificar(Integer id) { // UPDATE
		Categoria cat = buscarCategoriaPorId(id);
		if(cat!=null) {
			cat.setDescripcion("Trabajos de Finanzas");
			repo.save(cat);
		}
	}
	
	private void buscarPorId(Integer id) { //SELECT
		System.out.println("\tBuscar un registro por Id: " + id);
		Optional<Categoria> optional = repo.findById(id);
		if(optional.isPresent()) {
			System.out.println(optional.get());
		}else {
			System.out.println("Categoria no encontrada");
		}
	}
	
	
	private Categoria buscarCategoriaPorId(Integer id) { //SELECT
		System.out.println("\tBuscar un registro por Id: " + id);
		Optional<Categoria> optional = repo.findById(id);
		if(optional.isPresent()) {
			System.out.println(optional.get());
			return optional.get();
		}else {
			System.out.println("Categoria no encontrada");
			return null;
		}
	}
	
	
	private void guardar() { // INSERT
		System.out.println("\tInsertando un registro");
		Categoria cat = new Categoria();
		cat.setNombre("Finanzas");
		cat.setDescripcion("Trabajos relacionados con finanzas y contabilidad");
		repo.save(cat);
		System.out.println(cat);
	}
	
	private void eliminar(Integer id) { // DELETE
		System.out.println("\tEliminando un registro");	
		repo.deleteById(id);
	}
	
	
	
	/**
	 * Metodo que regresa una lista de 3 Categorias
	 * @return
	 */
	private List<Categoria> getListaCategorias(){
		List<Categoria> lista = new LinkedList<Categoria>();
		// Categoria 1
		Categoria cat1 = new Categoria();
		cat1.setNombre("Programador de Blockchain");
		cat1.setDescripcion("Trabajos relacionados con Bitcoin y Criptomonedas");
		
		// Categoria 2
		Categoria cat2 = new Categoria();
		cat2.setNombre("Soldador/Pintura");
		cat2.setDescripcion("Trabajos relacionados con soldadura, pintura y enderezado");
						
		// Categoria 3
		Categoria cat3 = new Categoria();
		cat3.setNombre("Ingeniero Industrial");
		cat3.setDescripcion("Trabajos relacionados con Ingenieria industrial.");
		
		lista.add(cat1);
		lista.add(cat2);
		lista.add(cat3);
		return lista;
	}

}
