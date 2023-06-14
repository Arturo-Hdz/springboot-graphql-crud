package com.app.ecommerce.repository;

import com.app.ecommerce.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, String> {
}
