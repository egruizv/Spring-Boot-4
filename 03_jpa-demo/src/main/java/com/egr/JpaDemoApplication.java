package com.egr;

import java.util.ArrayList;
import java.util.Date;
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
import com.egr.model.Perfil;
import com.egr.model.Usuario;
import com.egr.model.Vacante;
import com.egr.repository.CategoriasRepository;
import com.egr.repository.PerfilesRepository;
import com.egr.repository.UsuariosRepository;
import com.egr.repository.VacantesRepository;

//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.OneToOne;

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner { // TODO para que sirva de Console Application usamos la interface CommandLineRunner

	@Autowired
	private CategoriasRepository repoCategorias;
	
	@Autowired
	private VacantesRepository repoVacantes;
	
	
	@Autowired
	private UsuariosRepository repoUsuarios;
	
	@Autowired
	private PerfilesRepository repoPerfiles;
	
	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Codigo que se ejecuta cuando se arranca por ser  implements CommandLineRunner
		System.out.println("Ejemplos de Spring Data JPA");	
		/**
		 * CATEGORIAS
		 */
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
		//buscarTodosPaginacionOrdenados();
		/**
		 * VACANTES
		 */
		//buscarVacantes();
		//guardarVacante();
		/**
		 * PERFILES
		 */
		//crearPerfilesAplicacion();
		/**
		 * USUARIOS
		 */
		//crearUsuarioConDosPerfiles();
		//buscarUsuario();
		
		/**
		 * Query Method: 
		 */
		//buscarVacantePorEstatus();
		//buscarVacantePorDestacado();
		//buscarVacantePorDestacadoEstatus();
		//buscarVacantesSalario();
		buscarVacantesVariosEstatus();
		
	}
	
	// TODO Estamos usando desde el principio JpaRepositoru 
	
	
	/**
	 * USUARIOS
	 */
	
	//Buscar Usuario y desplegar sus perfiles asociados
	public void buscarUsuario() {
		Optional<Usuario> optional = repoUsuarios.findById(80);
		//QUERY : select u1_0.id,u1_0.email,u1_0.estatus,u1_0.fechaRegistro,u1_0.nombre,u1_0.password,u1_0.username,p1_0.idUsuario,p1_1.id,p1_1.perfil from Usuarios u1_0 left join UsuarioPerfil p1_0 on u1_0.id=p1_0.idUsuario left join perfiles p1_1 on p1_1.id=p1_0.idPerfil where u1_0.id=?
		if(optional.isPresent()) {
			Usuario u = optional.get();
			System.out.println("Usuario: " + u.getNombre());
			System.out.println("Perfiles asignados:");
			for (Perfil perfil : u.getPerfiles()) {
				System.out.println("\t -->" + perfil.getPerfil());
			}
			
		}else {
			System.out.println("Usuario no encontrado");
		}
	}
	
	
	/*
	 * metodo para crear un usuario con dos perfiles , en este caso (perfil ADMINISTRADOR y perfil USUARIO)
	 */
	@SuppressWarnings("unused")
	private void crearUsuarioConDosPerfiles() {
		Usuario user = new Usuario();
		user.setNombre("Ernesto Garcia");
		user.setEmail("egruizv@gmail.com");
		user.setFechaRegistro(new Date());
		user.setUsername("egruizv");
		user.setPassword("12345");
		user.setEstatus(1);
		
		Perfil per1 = new Perfil();
		per1.setId(2);
		
		Perfil per2 = new Perfil();
		per2.setId(3);
		
		user.agregarPerfilAlUsuario(per1);
		user.agregarPerfilAlUsuario(per2);
		
		repoUsuarios.save(user); 
		//QUERYS: : insert into Usuarios (email,estatus,fechaRegistro,nombre,password,username) values (?,?,?,?,?,?)
		//			insert into UsuarioPerfil (idUsuario,idPerfil) values (?,?)
		//			insert into UsuarioPerfil (idUsuario,idPerfil) values (?,?)
		
		
	}
	
	/**
	 * PERFILES
	 */
	@SuppressWarnings("unused")
	private void crearPerfilesAplicacion() {
		repoPerfiles.saveAll(getPerfilesAplicacion());
	}
	
	
	/**
	 * VACANTES
	 */
	
	
	/**
	 * Query Method: Buscar Vacante por varios Estatus
	 */
	@SuppressWarnings("unused")
	private void buscarVacantesVariosEstatus() {
		List<String> listaEstatus;
		listaEstatus = obtenerEstatus();
		List<Vacante> listaVacante = repoVacantes.findByEstatusIn(listaEstatus);
		//QUERY : 	select v1_0.id,v1_0.idCategoria,v1_0.descripcion,v1_0.destacado,v1_0.detalles,v1_0.estatus,v1_0.fecha,v1_0.imagen,v1_0.nombre,v1_0.salario from vacantes v1_0 where v1_0.estatus in (?,?)
		//			select c1_0.id,c1_0.descripcion,c1_0.nombre from categorias c1_0 where c1_0.id=?
		
		System.out.println("Registros encontrados: " + listaVacante.size());
		for (Vacante vac : listaVacante) {
			//System.out.println(vac.getId() + " " + vac.getNombre());
			System.out.println("ID VACANTE: " + vac.getId() + " " +  "\n\tNOMBRE VACANTE: "+ vac.getNombre() + " "+ 
					 "\n\tESTATUS: " + vac.getEstatus());
		}
	}
	
	
	/**
	 * Query Method: Buscar Vacante por Salario de forma Desc
	 */
	@SuppressWarnings("unused")
	private void buscarVacantesSalario() {
		List<Vacante> listaVacante = repoVacantes.findBySalarioBetweenOrderBySalarioDesc(7000, 14000);
		//QUERY :  select v1_0.id,v1_0.idCategoria,v1_0.descripcion,v1_0.destacado,v1_0.detalles,v1_0.estatus,v1_0.fecha,v1_0.imagen,v1_0.nombre,v1_0.salario from vacantes v1_0 where v1_0.salario between ? and ?
		
		System.out.println("Registros encontrados: " + listaVacante.size());
		for (Vacante vac : listaVacante) {
			//System.out.println(vac.getId() + " " + vac.getNombre());
			System.out.println("ID VACANTE: " + vac.getId() + " " +  "\n\tNOMBRE VACANTE: "+ vac.getNombre() + " "+ 
								"\n\tSALARIO: $" + vac.getSalario());
		}
	}
	
	/**
	 * Query Method: Buscar Vacante por Destacado y Estatus
	 */
	
	@SuppressWarnings("unused")
	private void buscarVacantePorDestacadoEstatus() {
		
		List<Vacante> listaVacante = repoVacantes.findByDestacadoAndEstatusOrderByIdDesc(0, "Aprobada");
		System.out.println("Registros encontrados: " + listaVacante.size());
		for (Vacante vac : listaVacante) {
			//System.out.println(vac.getId() + " " + vac.getNombre());
			System.out.println("ID VACANTE: " + vac.getId() + " " +  "\n\tNOMBRE VACANTE: "+ vac.getNombre() + " "+ 
								"\n\tDESTACADO: " + vac.getDestacado() + " " + "\n\tESTATUS: " + vac.getEstatus());
		}
	}
	
	
	/**
	 * Query Method: Buscar Vacante por Destacado
	 */
	@SuppressWarnings("unused")
	private void buscarVacantePorDestacado() {
		List<Vacante> listaVacante = repoVacantes.findByDestacado(1);		
		System.out.println("Registros encontrados: " + listaVacante.size());
		for (Vacante vac : listaVacante) {
			System.out.println("ID VACANTE: " + vac.getId() + " " +  "\n\tNOMBRE VACANTE: "+ vac.getNombre() + " "+ 
					"\n\tDESTACADO: " + vac.getDestacado());
		}
	}
	
	/**
	 * Query Method: Buscar Vacante por Estatus
	 */
	@SuppressWarnings("unused")
	private void buscarVacantePorEstatus() {
		List<Vacante> listaVacante = repoVacantes.findByEstatus("Creada");
		//QUERY : 	select v1_0.id,v1_0.idCategoria,v1_0.descripcion,v1_0.destacado,v1_0.detalles,v1_0.estatus,v1_0.fecha,v1_0.imagen,v1_0.nombre,v1_0.salario from vacantes v1_0 where v1_0.estatus=?
		//			select c1_0.id,c1_0.descripcion,c1_0.nombre from categorias c1_0 where c1_0.id=?
		
		System.out.println("Registros encontrados: " + listaVacante.size());
		for (Vacante vac : listaVacante) {
			//System.out.println(vac.getId() + " " + vac.getNombre());
			System.out.println("ID VACANTE: " + vac.getId() + " " +  "\n\tNOMBRE VACANTE: "+ vac.getNombre() + " "+ 
					 "\n\tESTATUS: " + vac.getEstatus());
		}
	}
	
	
	@SuppressWarnings("unused")
	private void guardarVacante() {
		Vacante vacante = new Vacante();
		vacante.setNombre("Profesor de Matematicas");
		vacante.setDescripcion("Escuela primaria solicita profesor para curso de Matematicas");
		vacante.setFecha(new Date());
		vacante.setSalario(8500.0);
		vacante.setEstatus("Aprobada");
		vacante.setDestacado(0);
		vacante.setImagen("escuela.png");
		vacante.setDetalles("<h1>Los requisitos para profesor de Matematicas</h1>");
		Categoria cat = new Categoria();
		cat.setId(15);
		vacante.setCategoria(cat);
		repoVacantes.save(vacante);
		//QUERY : insert into vacantes (idCategoria,descripcion,destacado,detalles,estatus,fecha,imagen,nombre,salario) values (?,?,?,?,?,?,?,?,?)
	}
	
	@SuppressWarnings("unused")
	private void buscarVacantes() {
		List<Vacante> listaVacante = repoVacantes.findAll();
		//QUERY @Transient : select v1_0.id,v1_0.descripcion,v1_0.destacado,v1_0.detalles,v1_0.estatus,v1_0.fecha,v1_0.imagen,v1_0.nombre,v1_0.salario from vacantes v1_0
		//QUERY @OneToOne + @JoinColumn(name="idCategoria") : select v1_0.id,v1_0.idCategoria,v1_0.descripcion,v1_0.destacado,v1_0.detalles,v1_0.estatus,v1_0.fecha,v1_0.imagen,v1_0.nombre,v1_0.salario from vacantes v1_0
		//													: select c1_0.id,c1_0.descripcion,c1_0.nombre from categorias c1_0 where c1_0.id=?
		for (Vacante vac : listaVacante) {
			//System.out.println(vac.getId() + " " + vac.getNombre());
			System.out.println("ID VACANTE: " + vac.getId() + " " +  "NOMBRE VACANTE: "+ vac.getNombre() + " "+ 
								"NOMBRE CATEGORIA " + vac.getCategoria().getNombre());
		}
	}
	
	/**
	 * CATEGORIAS
	 */
	@SuppressWarnings("unused")
	private void buscarTodosPaginacionOrdenados() {
	
		// Paginacion + Ordenacion 
		Page<Categoria> pageCategorias = repoCategorias.findAll(PageRequest.of(0, 5,Sort.by("nombre")));
	
		//TODO podemos preguntar usando el objeto pageCategorias el numero de paginas y el numero de registros
		System.out.println("TOTAL REGISTROS: "  + pageCategorias.getTotalElements());
		System.out.println("TOTAL PAGINAS: "  + pageCategorias.getTotalPages());
		
		for (Categoria cat : pageCategorias.getContent()) {
			System.out.println(cat.getId() + " " + cat.getNombre());
		}
	}
	
	
	@SuppressWarnings("unused")
	private void buscarTodosPaginacion() {
		/*
		  	pageNumber zero-based page number, must not be negative.--> Numero de pagina Nota la pagina 1 es el 0
			pageSize the size of the page to be returned, must be greater than 0.--> Numero de elementos por pagina. tiene q	ue ser positivo
		 */
		Page<Categoria> pageCategorias = repoCategorias.findAll(PageRequest.of(0, 3));
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
	
	@SuppressWarnings("unused")
	private void buscarTodosOrdenados() {
		//List<Categoria> listCategorias = repo.findAll(Sort.by("nombre"));// Si no ponemos nada es ascendiente	
		List<Categoria> listCategorias = repoCategorias.findAll(Sort.by("nombre").descending());//Descenciente 	
		// select c1_0.id,c1_0.descripcion,c1_0.nombre from categorias c1_0 order by c1_0.nombre
		for (Categoria cat : listCategorias) {
			System.out.println(cat.getId() + " " + cat.getNombre());
		}
	}
	
	
	@SuppressWarnings("unused")
	private void borrarTodosEnBloque() { //TODO Usar con precaucion ya que borra todos en bloque (Deletes all entities in a batch call.)
		repoCategorias.deleteAllInBatch();// delete c1_0 from categorias c1_0
	}
	
	
	@SuppressWarnings("unused")
	private void saveAll(List<Categoria> categorias) {
		repoCategorias.saveAll(categorias);
	}
	
	@SuppressWarnings("unused")
	private void existeId(Integer id) {
		boolean bExiste = repoCategorias.existsById(id);
		System.out.println("¿La categoria con ID: " + id +" existe? :" + bExiste);
		
	}
	
	@SuppressWarnings("unused")
	private void buscarTodos() {
		List<Categoria> listCategorias = repoCategorias.findAll();		
		for (Categoria cat : listCategorias) {
			System.out.println(cat);
			System.out.println(cat.getId() + " " + cat.getNombre());
		}
		
	}
	
	
	
	@SuppressWarnings("unused")
	private void encontrarPorIds(List<Integer> ids) {
		List<Categoria> categorias = repoCategorias.findAllById(ids);
		for (Categoria cat : categorias) {
			System.out.println(cat);
		}
	}
	
	@SuppressWarnings("unused")
	private void eliminarTodos() { //TODO cuando hay pocos registros ya que elimina uno a uno
		repoCategorias.deleteAll();
	}
	
	@SuppressWarnings("unused")
	private void contar() {
		long count = repoCategorias.count();
		System.out.println("Total categorias :" + count);
	}
	
	@SuppressWarnings("unused")
	private void modificar(Integer id) { // UPDATE
		Categoria cat = buscarCategoriaPorId(id);
		if(cat!=null) {
			cat.setDescripcion("Trabajos de Finanzas");
			repoCategorias.save(cat);
		}
	}
	
	@SuppressWarnings("unused")
	private void buscarPorId(Integer id) { //SELECT
		System.out.println("\tBuscar un registro por Id: " + id);
		Optional<Categoria> optional = repoCategorias.findById(id);
		if(optional.isPresent()) {
			System.out.println(optional.get());
		}else {
			System.out.println("Categoria no encontrada");
		}
	}
	
	
	private Categoria buscarCategoriaPorId(Integer id) { //SELECT
		System.out.println("\tBuscar un registro por Id: " + id);
		Optional<Categoria> optional = repoCategorias.findById(id);
		if(optional.isPresent()) {
			System.out.println(optional.get());
			return optional.get();
		}else {
			System.out.println("Categoria no encontrada");
			return null;
		}
	}
	
	
	@SuppressWarnings("unused")
	private void guardar() { // INSERT
		System.out.println("\tInsertando un registro");
		Categoria cat = new Categoria();
		cat.setNombre("Finanzas");
		cat.setDescripcion("Trabajos relacionados con finanzas y contabilidad");
		repoCategorias.save(cat);
		System.out.println(cat);
	}
	
	@SuppressWarnings("unused")
	private void eliminar(Integer id) { // DELETE
		System.out.println("\tEliminando un registro");	
		repoCategorias.deleteById(id);
	}
	
	
	
	/**
	 * Metodo que regresa una lista de 3 Categorias
	 * @return
	 */
	@SuppressWarnings("unused")
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
	
	
	/**
	 * Metodo que regresa una lista de objetos de tipo Perfil que representa los diferentes PERFILES 
	 * O ROLES que tendremos en nuestra aplicación de Empleos
	 * @return
	 */
	private List<Perfil> getPerfilesAplicacion(){		
		List<Perfil> lista = new LinkedList<Perfil>();
		Perfil per1 = new Perfil();
		per1.setPerfil("SUPERVISOR");
		
		Perfil per2 = new Perfil();
		per2.setPerfil("ADMINISTRADOR");
		
		Perfil per3 = new Perfil();
		per3.setPerfil("USUARIO");
		
		lista.add(per1);
		lista.add(per2);
		lista.add(per3);
		
		return lista;
	}

	private List<String> obtenerEstatus() {
		List<String> lista = new ArrayList<String>();
		lista.add("Eliminada");
		lista.add("Creada");
		return lista;
	}

}
