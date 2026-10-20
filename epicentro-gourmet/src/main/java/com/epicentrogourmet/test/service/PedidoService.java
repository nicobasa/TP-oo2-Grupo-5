package com.epicentrogourmet.test.service;

import com.epicentrogourmet.test.entity.Pedido;
import com.epicentrogourmet.test.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository repository;

    public PedidoService(PedidoRepository repository) {
        this.repository = repository;
    }

    public Pedido agregar(Pedido objeto) {
        return repository.save(objeto);
    }

    public void actualizar(Pedido objeto) {
        repository.save(objeto);
    }

    public void eliminar(Pedido objeto) {
        repository.delete(objeto);
    }

    public Pedido traer(Long idPedido) {
        return repository.findById(idPedido).orElse(null);
    }

    public List<Pedido> traer() {
        return repository.findAll();
    }
}