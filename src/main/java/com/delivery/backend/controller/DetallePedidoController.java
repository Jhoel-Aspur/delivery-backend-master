package com.delivery.backend.controller;

import com.delivery.backend.dto.DetallePedidoDTO;
import com.delivery.backend.model.DetallePedido;
import com.delivery.backend.model.Pedido;
import com.delivery.backend.model.Producto;
import com.delivery.backend.service.DetallePedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/detallePedidos")
public class DetallePedidoController {

    private final DetallePedidoService detallePedidoService;

    public DetallePedidoController(DetallePedidoService detallePedidoService) {
        this.detallePedidoService = detallePedidoService;
    }

    @GetMapping
    public List<DetallePedidoDTO> listarDetallesPedido() {
        List<DetallePedido> detalles = detallePedidoService.listarTodos();
        return detalles.stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<?> crearDetallePedido(@RequestBody DetallePedidoDTO detallePedidoDTO) {
        DetallePedido detalle = convertirAEntidad(detallePedidoDTO);

        Optional<DetallePedido> detalleCreado = detallePedidoService.crearDetallePedido(detalle);

        if (detalleCreado.isPresent()) {
            return new ResponseEntity<>(convertirADTO(detalleCreado.get()), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Pedido o producto no válido", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDetallePedido(@PathVariable Long id) {
        Optional<DetallePedido> detalle = detallePedidoService.obtenerPorId(id);
        if (detalle.isPresent()) {
            detallePedidoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    private DetallePedidoDTO convertirADTO(DetallePedido detalle) {
        return new DetallePedidoDTO(
                detalle.getId(),
                detalle.getPedido() != null ? detalle.getPedido().getId() : null,
                detalle.getProducto() != null ? detalle.getProducto().getId() : null,
                detalle.getCantidad(),
                detalle.getPrecio()
        );
    }

    private DetallePedido convertirAEntidad(DetallePedidoDTO dto) {
        DetallePedido detalle = new DetallePedido();

        detalle.setId(dto.getId());

        Pedido pedido = new Pedido();
        pedido.setId(dto.getPedidoId());
        detalle.setPedido(pedido);

        Producto producto = new Producto();
        producto.setId(dto.getProductoId());
        detalle.setProducto(producto);

        detalle.setCantidad(dto.getCantidad());
        detalle.setPrecio(dto.getPrecio());

        return detalle;
    }
}
