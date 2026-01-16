package com.egr.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.egr.model.Categoria;



@Service
public class CategoriasServiceImpl implements ICategoriasService {
	
	private List<Categoria> listaCotegorias = null;

	//Constructor
	public CategoriasServiceImpl() {
		listaCotegorias = new LinkedList<Categoria>();
		
		// Creamos algunas Categorias para poblar la lista ...
		
		// Categoria 1
		Categoria cat1 = new Categoria();
		cat1.setId(1);
		cat1.setNombre("Contabilidad");
		cat1.setDescripcion("Descripcion de la categoria Contabilidad");
		
		// Categoria 2
		Categoria cat2 = new Categoria();
		cat2.setId(2);
		cat2.setNombre("Ventas");
		cat2.setDescripcion("Trabajos relacionados con Ventas");
		
					
		// Categoria 3
		Categoria cat3 = new Categoria();
		cat3.setId(3);
		cat3.setNombre("Comunicaciones");
		cat3.setDescripcion("Trabajos relacionados con Comunicaciones");
		
		// Categoria 4
		Categoria cat4 = new Categoria();
		cat4.setId(4);
		cat4.setNombre("Arquitectura");
		cat4.setDescripcion("Trabajos de Arquitectura");
		
		// Categoria 5
		Categoria cat5 = new Categoria();
		cat5.setId(5);
		cat5.setNombre("Educacion");
		cat5.setDescripcion("Maestros, tutores, etc");
		
		// Categoria 6
		Categoria cat6 = new Categoria();
		cat6.setId(6);
		cat6.setNombre("Desarrollo de software");
		cat6.setDescripcion("Trabajo para programadores");
		
		/**
		 * Agregamos los 5 objetos de tipo Categoria a la lista ...
		 */
		listaCotegorias.add(cat1);			
		listaCotegorias.add(cat2);
		listaCotegorias.add(cat3);
		listaCotegorias.add(cat4);
		listaCotegorias.add(cat5);
		listaCotegorias.add(cat6);

	}
	
	@Override
	public void guardar(Categoria categoria) {
		// Guardaremos en un futuro la vacante en BBDD
		
		//Nota: Adicionalmente asocio un id que será +1 del maximo que tengamos
			List<Categoria> ls =  buscarTodas();
			int idAux = ls.size();
			categoria.setId(idAux+1);
		//Fin Nota
		System.out.println("Catgegoria" + categoria);
		
		// Ahora lo metemos en una lista
		listaCotegorias.add(categoria);

	}

	@Override
	public List<Categoria> buscarTodas() {
		// TODO Auto-generated method stub
		return listaCotegorias;
	}

	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		for(Categoria cat : listaCotegorias) {
			if(cat.getId()==idCategoria) {
				return cat;
			}
		}
		return null;
	}

}
