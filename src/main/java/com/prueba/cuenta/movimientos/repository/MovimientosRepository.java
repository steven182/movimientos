package com.prueba.cuenta.movimientos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.cuenta.movimientos.entity.Movimientos;

@Repository
public interface MovimientosRepository extends JpaRepository<Movimientos, Long> {

}
