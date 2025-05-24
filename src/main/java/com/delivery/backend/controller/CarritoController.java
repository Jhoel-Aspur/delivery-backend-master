package com.delivery.backend.controller;

import com.delivery.backend.model.Carrito;
import com.delivery.backend.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carritos")  // Mapeamos la ruta base para este controlador
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @GetMapping
    public List<Carrito> obtenerTodosCarritos() {
        return carritoService.obtenerTodos();
    }

    @PostMapping
    public Carrito crearCarrito(@RequestBody Carrito carrito) {
        return carritoService.crearCarrito(carrito);
    }

    @DeleteMapping("/{id}")
    public void eliminarCarrito(@PathVariable Long id) {
        carritoService.eliminarCarrito(id);
    }

    @PutMapping("/{id}")
    public Carrito actualizarCarrito(@PathVariable Long id, @RequestBody Carrito carrito) {
        return carritoService.actualizarCarrito(id, carrito);
    }
}
