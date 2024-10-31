package com.prueba.cuenta.movimientos.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.cuenta.movimientos.dto.CuentaDto;
import com.prueba.cuenta.movimientos.dto.EstadoCuentaDto;
import com.prueba.cuenta.movimientos.entity.Cuenta;
import com.prueba.cuenta.movimientos.exception.AdvencedException;
import com.prueba.cuenta.movimientos.repository.CuentaRepository;
import com.prueba.cuenta.movimientos.service.CuentaService;

import jakarta.persistence.Tuple;

@Service
public class CuentaServiceImpl implements CuentaService {

	@Autowired
	private CuentaRepository cuentaRepository;

	@Override
	public void crearCuenta(CuentaDto cuentaDto) {
		final var cuenta = new Cuenta();
		cuenta.setEstado(cuentaDto.isEstado());
		cuenta.setNumeroCuenta(cuentaDto.getNumeroCuenta());
		cuenta.setSaldoInicial(cuentaDto.getSaldoDisponible());
		cuenta.setTipoCuenta(cuentaDto.getTipo());
		cuenta.setCliente(cuentaDto.getClienteId());
		final var existeCliente = cuentaRepository.existeCliente(cuentaDto.getClienteId());
		if (existeCliente <= 0) {
			throw new AdvencedException("El cliente no coinciden.");
		}
		cuentaRepository.save(cuenta);
	}

	@Override
	public void actualizarCuenta(long cuentaId, CuentaDto cuentaDto) {
		final var cuenta = cuentaRepository.findById(cuentaId)
				.orElseThrow(() -> new AdvencedException("Error: Cliente no encontrado para ID " + cuentaId));
		if (cuentaDto.getClienteId() != cuenta.getCliente().longValue()) {
			throw new AdvencedException("El cliente no coinciden.");
		}
		cuenta.setEstado(cuentaDto.isEstado());
		cuenta.setNumeroCuenta(cuentaDto.getNumeroCuenta());
		cuenta.setSaldoInicial(cuentaDto.getSaldoDisponible());
		cuenta.setTipoCuenta(cuentaDto.getTipo());
		cuenta.setCliente(cuentaDto.getClienteId());
		cuentaRepository.save(cuenta);
	}

	@Override
	public List<CuentaDto> obtenerCuentas() {
		final List<CuentaDto> listaRespuesta = new ArrayList<>();
		final var listaCuentas = cuentaRepository.findAll();
		listaCuentas.forEach(cuenta -> {
			final var cuentaDto = new CuentaDto();
			cuentaDto.setClienteId(cuenta.getCliente());
			cuentaDto.setEstado(cuenta.getEstado());
			cuentaDto.setNumeroCuenta(cuenta.getNumeroCuenta());
			cuentaDto.setSaldoDisponible(cuenta.getSaldoInicial());
			cuentaDto.setTipo(cuenta.getTipoCuenta());
			listaRespuesta.add(cuentaDto);
		});
		return listaRespuesta;
	}

	@Override
	public void borrarCuenta(Long cuentaId) {
		final var cuenta = cuentaRepository.findById(cuentaId)
				.orElseThrow(() -> new AdvencedException("Error: Cliente no encontrado para ID " + cuentaId));
		cuentaRepository.delete(cuenta);
	}

	@Override
	public List<EstadoCuentaDto> obtenerEstadoCuenta(Long clienteId, String fechaInicio, String fechaFin) {
		List<Tuple> resultados = cuentaRepository.obtenerEstadoCuenta(clienteId, fechaInicio, fechaFin);
		List<EstadoCuentaDto> estadoCuenta = new ArrayList<>();
		for (Tuple fila : resultados) {
			EstadoCuentaDto dto = new EstadoCuentaDto();
			dto.setNombreCliente(fila.get("nombre_cliente", String.class));
			dto.setIdentificacion(fila.get("identificacion", String.class));
			dto.setTelefono(fila.get("direccion", String.class));
			dto.setTelefono(fila.get("telefono", String.class));
			dto.setNumeroCuenta(fila.get("numero_cuenta", String.class));
			dto.setTipoCuenta(fila.get("tipo_cuenta", String.class));
			dto.setSaldoInicial(fila.get("saldo_inicial", BigDecimal.class));
			dto.setEstado(fila.get("estado", Boolean.class));
			dto.setFechaMovimiento(fila.get("fecha", Timestamp.class));
			dto.setTipoMovimiento(fila.get("tipo_movimiento", String.class));
			dto.setValorMovimiento(fila.get("valor", BigDecimal.class));
			estadoCuenta.add(dto);
		}
		return estadoCuenta;
	}

}
