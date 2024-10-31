package com.prueba.cuenta.movimientos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.cuenta.movimientos.dto.MovimientoDto;
import com.prueba.cuenta.movimientos.service.MovimientosService;

@RestController
@RequestMapping("/movimientos")
public class MovimientosController {

	@Autowired
	private MovimientosService movimientosService;

	@PostMapping("/save-movimiento")
	public ResponseEntity<Void> saveMovimientos(@RequestBody MovimientoDto movimientosDto) {
		movimientosService.crearMovimiento(movimientosDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/update-movimiento/{movimientoId}")
	public ResponseEntity<Void> updateCuanta(@PathVariable Long movimientoId,
			@RequestBody MovimientoDto movimientosDto) {
		movimientosService.actualizarMovimiento(movimientoId, movimientosDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/get-movimientos")
	public ResponseEntity<List<MovimientoDto>> getCuentas() {
		return new ResponseEntity<>(movimientosService.obtenerMovimientos(), HttpStatus.OK);
	}

	@DeleteMapping("/delete-movimiento/{movimientoId}")
	public ResponseEntity<Void> deleteCuenta(@PathVariable Long movimientoId) {
		movimientosService.borrarMovimiento(movimientoId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
