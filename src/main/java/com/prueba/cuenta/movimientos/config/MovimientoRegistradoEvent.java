package com.prueba.cuenta.movimientos.config;

import java.io.Serializable;

public class MovimientoRegistradoEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String numeroCuenta;
	private String tipoMovimiento;
	private Double valor;
	private Double saldoFinal;
	private String fecha;

	public MovimientoRegistradoEvent(String numeroCuenta, String tipoMovimiento, Double valor, Double saldoFinal,
			String fecha) {
		super();
		this.numeroCuenta = numeroCuenta;
		this.tipoMovimiento = tipoMovimiento;
		this.valor = valor;
		this.saldoFinal = saldoFinal;
		this.fecha = fecha;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getSaldoFinal() {
		return saldoFinal;
	}

	public void setSaldoFinal(Double saldoFinal) {
		this.saldoFinal = saldoFinal;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

}
