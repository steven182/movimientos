package com.prueba.cuenta.movimientos.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MovimientoDto {

	private String tipo;
	private BigDecimal valor;
	private Long cuentaId;
	private LocalDateTime fecha;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Long getCuentaId() {
		return cuentaId;
	}

	public void setCuentaId(Long cuentaId) {
		this.cuentaId = cuentaId;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

}
