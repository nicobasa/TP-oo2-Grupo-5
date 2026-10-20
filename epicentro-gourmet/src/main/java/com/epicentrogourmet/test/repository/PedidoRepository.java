package com.epicentrogourmet.test.repository;

import com.epicentrogourmet.test.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
