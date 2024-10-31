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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.cuenta.movimientos.dto.CuentaDto;
import com.prueba.cuenta.movimientos.dto.EstadoCuentaDto;
import com.prueba.cuenta.movimientos.service.CuentaService;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {

	@Autowired
	private CuentaService cuentaService;

	@PostMapping("/save-cuenta")
	public ResponseEntity<Void> saveCuenta(@RequestBody CuentaDto cuenta) {
		cuentaService.crearCuenta(cuenta);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/update-cuenta/{cuentaId}")
	public ResponseEntity<Void> updateCuanta(@PathVariable Long cuentaId, @RequestBody CuentaDto cuenta) {
		cuentaService.actualizarCuenta(cuentaId, cuenta);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/get-cuentas")
	public ResponseEntity<List<CuentaDto>> getCuentas() {
		return new ResponseEntity<>(cuentaService.obtenerCuentas(), HttpStatus.OK);
	}

	@DeleteMapping("/delete-cuenta/{cuentaId}")
	public ResponseEntity<Void> deleteCuenta(@PathVariable Long cuentaId) {
		cuentaService.borrarCuenta(cuentaId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/reportes/{clienteId}")
	public ResponseEntity<List<EstadoCuentaDto>> getEstadoCuenta(@PathVariable Long clienteId,
			@RequestParam("fechaInicio") String fechaInicio, @RequestParam("fechaFin") String fechaFin) {
		return new ResponseEntity<>(cuentaService.obtenerEstadoCuenta(clienteId, fechaInicio, fechaFin), HttpStatus.OK);
	}

}
