package com.delivery.backend.service;

import com.delivery.backend.model.DetallePedido;
import com.delivery.backend.repository.DetallePedidoRepository;
import com.delivery.backend.repository.PedidoRepository;
import com.delivery.backend.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetallePedidoService {

    private final DetallePedidoRepository detallePedidoRepository;
    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;

    public DetallePedidoService(DetallePedidoRepository detallePedidoRepository,
                                PedidoRepository pedidoRepository,
                                ProductoRepository productoRepository) {
        this.detallePedidoRepository = detallePedidoRepository;
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
    }

    public List<DetallePedido> listarTodos() {
        return detallePedidoRepository.findAll();
    }

    public Optional<DetallePedido> obtenerPorId(Long id) {
        return detallePedidoRepository.findById(id);
    }

    public DetallePedido guardar(DetallePedido detallePedido) {
        return detallePedidoRepository.save(detallePedido);
    }

    public void eliminar(Long id) {
        detallePedidoRepository.deleteById(id);
    }

    public Optional<DetallePedido> crearDetallePedido(DetallePedido detallePedido) {
        if (detallePedido.getPedido() == null || detallePedido.getProducto() == null) {
            return Optional.empty();
        }
        if (!pedidoRepository.existsById(detallePedido.getPedido().getId())) {
            return Optional.empty();
        }
        if (!productoRepository.existsById(detallePedido.getProducto().getId())) {
            return Optional.empty();
        }

        DetallePedido nuevoDetalle = detallePedidoRepository.save(detallePedido);
        return Optional.of(nuevoDetalle);
    }
}
