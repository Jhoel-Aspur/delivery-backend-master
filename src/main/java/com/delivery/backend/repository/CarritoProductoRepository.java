package com.delivery.backend.repository;

import com.delivery.backend.model.CarritoProducto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarritoProductoRepository extends JpaRepository<CarritoProducto, Long> {
}
