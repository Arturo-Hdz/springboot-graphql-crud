package com.app.ecommerce.controller;

import com.app.ecommerce.dto.ProductoRequest;
import com.app.ecommerce.entities.Categoria;
import com.app.ecommerce.entities.Producto;
import com.app.ecommerce.repository.CategoriaRepository;
import com.app.ecommerce.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class ProductoGraphQLController {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @QueryMapping
    public List<Producto> listarProductos(){
        return productoRepository.findAll();
    }

    @QueryMapping
    public Producto listarProductoPorId(@Argument String id){
        return productoRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format("Producto %s no encontrado", id))
        );
    }

    @QueryMapping
    public List<Categoria> listarCategorias(){
        return categoriaRepository.findAll();
    }

    @QueryMapping
    public Categoria listarCategoriaPorId(@Argument Long id){
        return categoriaRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format("Categoria %s no encontrada", id))
        );
    }

    @MutationMapping
    public Producto guardarProducto(@Argument ProductoRequest productoRequest){
        Categoria categoria = categoriaRepository.findById(productoRequest.categoriaId()).orElseThrow(null);
        Producto productoBBDD = new Producto();
        productoBBDD.setId(UUID.randomUUID().toString());
        productoBBDD.setNombre(productoRequest.nombre());
        productoBBDD.setPrecio(productoRequest.precio());
        productoBBDD.setCantidad(productoRequest.cantidad());
        productoBBDD.setCategoria(categoria);
        return productoRepository.save(productoBBDD);
    }

    @MutationMapping
    public Producto actualizarProducto(@Argument String id, @Argument ProductoRequest productoRequest){
        Categoria categoria = categoriaRepository.findById(productoRequest.categoriaId()).orElseThrow(null);
        Producto productoBBDD = new Producto();
        productoBBDD.setId(id);
        productoBBDD.setNombre(productoRequest.nombre());
        productoBBDD.setPrecio(productoRequest.precio());
        productoBBDD.setCantidad(productoRequest.cantidad());
        productoBBDD.setCategoria(categoria);
        return productoRepository.save(productoBBDD);
    }

    @MutationMapping
    public void eliminarProducto(@Argument String id){
        productoRepository.deleteById(id);
    }

}
