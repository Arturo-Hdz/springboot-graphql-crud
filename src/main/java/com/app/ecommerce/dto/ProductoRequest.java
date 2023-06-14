package com.app.ecommerce.dto;

public record ProductoRequest(

    String id,

    String nombre,
    double precio,
    int cantidad,
    Long categoriaId
){
}
