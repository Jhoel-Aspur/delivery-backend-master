package com.delivery.backend.service;

import com.delivery.backend.model.CarritoProducto;
import com.delivery.backend.repository.CarritoProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarritoProductoService {

    @Autowired
    private CarritoProductoRepository carritoProductoRepository;

    public List<CarritoProducto> obtenerTodos() {
        return carritoProductoRepository.findAll();
    }

    public CarritoProducto agregarProducto(CarritoProducto carritoProducto) {
        return carritoProductoRepository.save(carritoProducto);
    }

    public void eliminarProducto(Long id) {
        carritoProductoRepository.deleteById(id);
    }
}
