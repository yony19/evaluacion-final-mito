package com.mitocode.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "farmacia")
public class Farmacia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idFarmacia;
	
	@Column(name = "nombre_farmacia")
	private String nombre_farmacia;
	
	@Column(name = "precio")
	private Float precio;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne
	@JoinColumn(name = "id_medicamento", nullable = false)
	private Medicamento medicamento;

	public Integer getIdFarmacia() {
		return idFarmacia;
	}

	public void setIdFarmacia(Integer idFarmacia) {
		this.idFarmacia = idFarmacia;
	}

	public String getNombre_farmacia() {
		return nombre_farmacia;
	}

	public void setNombre_farmacia(String nombre_farmacia) {
		this.nombre_farmacia = nombre_farmacia;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}
	
}
