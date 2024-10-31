package com.prueba.cuenta.movimientos.service;

import java.util.List;

import com.prueba.cuenta.movimientos.dto.CuentaDto;
import com.prueba.cuenta.movimientos.dto.EstadoCuentaDto;

public interface CuentaService {

	void crearCuenta(CuentaDto cuentaDto);

	void actualizarCuenta(long cuentaId, CuentaDto cuentaDto);

	List<CuentaDto> obtenerCuentas();

	void borrarCuenta(Long cuentaId);

	List<EstadoCuentaDto> obtenerEstadoCuenta(Long clienteId, String fechaInicio, String fechaFin);

}
