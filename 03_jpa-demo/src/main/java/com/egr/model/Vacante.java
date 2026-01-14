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
	
	private Categoria categoria; // TODO que sea de tipo Categoria
	private String estatus;
	private String detalles;
	
	
	
	
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
	
	
	
	
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public String getDetalles() {
		return detalles;
	}
	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}
	@Override
	public String toString() {
		return "Vacante [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", fecha=" + fecha
				+ ", salario=" + salario + ", destacado=" + destacado + ", imagen=" + imagen + ", categoria="
				+ categoria + ", estatus=" + estatus + ", detalles=" + detalles + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
