package com.egr.model;

import java.util.Date;

// TODO estructura de Bean 
public class Vacante {
	
	private Integer id; 
	private String nombre;// Nombre de la vacante
	private String descripcion; // Descripcion de la vacante u oferta 
	private Date fecha; //Fecha de publicacion
	private Double salario; //Salario de la vacante u oferta de trabajo 
	private Integer destacado;
	private String imagen= "no-image.png"; // Imagen de la empresa que realiza la oferta Nota: Se inicializa a no-image.png para que las emperesas que no tengamos imagn salga la no-image.png
	
	//GETTERS y SETTERS
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Double getSalario() {
		return salario;
	}
	public void setSalario(Double salario) {
		this.salario = salario;
	}
	
	public Integer getDestacado() {
		return destacado;
	}
	public void setDestacado(Integer destacado) {
		this.destacado = destacado;
	}
	
	
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	@Override
	public String toString() {
		return "Vacante [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", fecha=" + fecha
				+ ", salario=" + salario + ", destacado=" + destacado + ", imagen=" + imagen + "]";
	}
	
	
	
	
	
	
	
	
	
	

}
