package com.prueba.cuenta.movimientos.service;

import java.util.List;

import com.prueba.cuenta.movimientos.dto.MovimientoDto;

public interface MovimientosService {

	void crearMovimiento(MovimientoDto movimientoDto);

	void actualizarMovimiento(long movimientoId, MovimientoDto movimientoDto);

	List<MovimientoDto> obtenerMovimientos();

	void borrarMovimiento(Long movimientoId);

}
