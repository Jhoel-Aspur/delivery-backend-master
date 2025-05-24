package com.delivery.backend.controller;

import com.delivery.backend.model.CarritoProducto;
import com.delivery.backend.service.CarritoProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carritoProductos")
public class CarritoProductoController {

    @Autowired
    private CarritoProductoService carritoProductoService;

    @GetMapping
    public List<CarritoProducto> obtenerProductosCarrito() {
        return carritoProductoService.obtenerTodos();
    }

    @PostMapping
    public CarritoProducto agregarProductoAlCarrito(@RequestBody CarritoProducto carritoProducto) {
        return carritoProductoService.agregarProducto(carritoProducto);
    }

    @DeleteMapping("/{id}")
    public void eliminarProductoDelCarrito(@PathVariable Long id) {
        carritoProductoService.eliminarProducto(id);
    }
}
