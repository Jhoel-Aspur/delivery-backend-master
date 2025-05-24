package com.delivery.backend.service;

import com.delivery.backend.model.Carrito;
import com.delivery.backend.repository.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    // Obtener todos los carritos
    public List<Carrito> obtenerTodos() {
        return carritoRepository.findAll();
    }

    // Crear un carrito nuevo
    public Carrito crearCarrito(Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    // Eliminar un carrito
    public void eliminarCarrito(Long id) {
        carritoRepository.deleteById(id);
    }

    // Actualizar un carrito
    public Carrito actualizarCarrito(Long id, Carrito carrito) {
        Optional<Carrito> carritoExistente = carritoRepository.findById(id);
        if (carritoExistente.isPresent()) {
            Carrito carritoActualizado = carritoExistente.get();
            carritoActualizado.setTotal(carrito.getTotal());
            carritoActualizado.setUsuario(carrito.getUsuario());
            return carritoRepository.save(carritoActualizado);
        } else {
            throw new RuntimeException("Carrito no encontrado con id: " + id);
        }
    }
}
