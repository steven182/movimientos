package com.prueba.cuenta.movimientos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prueba.cuenta.movimientos.entity.Cuenta;

import jakarta.persistence.Tuple;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

	@Query(value = "select count(*) from mi_esquema.cliente where cliente_id = :clienteId;", nativeQuery = true)
	int existeCliente(@Param("clienteId") Long clienteId);

	@Query(value = """
			SELECT
			    p.nombre AS nombre_cliente,
			    p.identificacion,
			    p.direccion,
			    p.telefono,
			    c.numero_cuenta,
			    c.tipo_cuenta,
			    c.saldo_inicial,
			    c.estado,
			    m.fecha,
			    m.tipo_movimiento,
			    m.valor
			FROM
			    mi_esquema.cliente cl
			JOIN
			    mi_esquema.persona p ON cl.persona_id = p.persona_id
			JOIN
			    mi_esquema.cuenta c ON cl.cliente_id = c.cliente_id
			JOIN
			    mi_esquema.movimientos m ON c.cuenta_id = m.cuenta_id
			WHERE
				cl.cliente_id = :clienteId
			AND DATE(m.fecha) BETWEEN CAST(:fechaInicio AS DATE) AND CAST(:fechaFin AS DATE)
			ORDER BY
			    m.fecha
			""", nativeQuery = true)
	List<Tuple> obtenerEstadoCuenta(@Param("clienteId") Long clienteId, @Param("fechaInicio") String fechaInicio,
			@Param("fechaFin") String fechaFin);
	
}
