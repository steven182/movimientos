package com.prueba.cuenta.movimientos.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.cuenta.movimientos.config.MovimientoRegistradoEvent;
import com.prueba.cuenta.movimientos.dto.MovimientoDto;
import com.prueba.cuenta.movimientos.entity.Movimientos;
import com.prueba.cuenta.movimientos.exception.AdvencedException;
import com.prueba.cuenta.movimientos.repository.CuentaRepository;
import com.prueba.cuenta.movimientos.repository.MovimientosRepository;
import com.prueba.cuenta.movimientos.service.MovimientosService;

@Service
public class MovimientosServiceImpl implements MovimientosService {

	@Autowired
	private MovimientosRepository movimientosRepository;

	@Autowired
	private CuentaRepository cuentaRepository;

	@Autowired
	private MovimientoEventPublisher eventPublisher;

	@Override
	public void crearMovimiento(MovimientoDto movimientoDto) {
		final var cuenta = cuentaRepository.findById(movimientoDto.getCuentaId());
		if (!cuenta.isPresent()) {
			throw new AdvencedException("La cuenta no existe");
		}
		if (cuenta.get().getSaldoInicial().intValue() <= 0) {
			throw new AdvencedException("Saldo no disponible");
		}
		final var movimiento = new Movimientos();
		final var saldoRestante = cuenta.get().getSaldoInicial().subtract(movimientoDto.getValor());
		cuenta.get().setSaldoInicial(saldoRestante);
		movimiento.setCuenta(cuenta.get());
		movimiento.setTipoMovimiento(movimientoDto.getTipo());
		movimiento.setValor(movimientoDto.getValor());
		cuentaRepository.save(cuenta.get());
		final var nuevoMovimiento = movimientosRepository.save(movimiento);
		registrarEvento(nuevoMovimiento);
	}

	@Override
	public void actualizarMovimiento(long movimientoId, MovimientoDto movimientoDto) {
		final var movimiento = movimientosRepository.findById(movimientoId)
				.orElseThrow(() -> new AdvencedException("Error: Movimiento no encontrado para ID " + movimientoId));
		if (movimientoDto.getCuentaId() != movimiento.getCuenta().getCuentaId().longValue()) {
			throw new AdvencedException("Las Cuentas no coinciden.");
		}
		movimiento.setTipoMovimiento(movimientoDto.getTipo());
		movimientosRepository.save(movimiento);
	}

	@Override
	public List<MovimientoDto> obtenerMovimientos() {
		final List<MovimientoDto> listaRespuesta = new ArrayList<>();
		final var listaMovimientos = movimientosRepository.findAll();
		listaMovimientos.forEach(movimiento -> {
			final var movimientoDto = new MovimientoDto();
			movimientoDto.setCuentaId(movimiento.getCuenta().getCuentaId());
			movimientoDto.setTipo(movimiento.getTipoMovimiento());
			movimientoDto.setValor(movimiento.getValor());
			movimientoDto.setFecha(movimiento.getFecha());
			listaRespuesta.add(movimientoDto);
		});
		return listaRespuesta;
	}

	@Override
	public void borrarMovimiento(Long movimientoId) {
		final var movimiento = movimientosRepository.findById(movimientoId)
				.orElseThrow(() -> new AdvencedException("Error: Movimiento no encontrado para ID " + movimientoId));
		movimientosRepository.delete(movimiento);
	}

	private void registrarEvento(Movimientos nuevoMovimiento) {
		MovimientoRegistradoEvent evento = new MovimientoRegistradoEvent(nuevoMovimiento.getCuenta().getNumeroCuenta(),
				nuevoMovimiento.getTipoMovimiento(), nuevoMovimiento.getValor().doubleValue(),
				nuevoMovimiento.getCuenta().getSaldoInicial().doubleValue(), nuevoMovimiento.getFecha().toString());
		eventPublisher.enviarMovimientoRegistradoEvento(evento);
	}

}
