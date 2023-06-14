package com.app.ecommerce;

import com.app.ecommerce.entities.Categoria;
import com.app.ecommerce.entities.Producto;
import com.app.ecommerce.repository.CategoriaRepository;
import com.app.ecommerce.repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class InventarioServiceGraphqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventarioServiceGraphqlApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(CategoriaRepository categoriaRepository, ProductoRepository productoRepository){
		Random random = new Random();
		return args -> {
			List.of("Computadoras","impresoras","Smartphones").forEach(cat -> {
				Categoria categoria = Categoria.builder().nombre(cat).build();
				categoriaRepository.save(categoria);
			});
			categoriaRepository.findAll().forEach(categoria -> {
				for(int i=0; i<10; i++){
					Producto producto = Producto.builder()
							.id(UUID.randomUUID().toString())
							.nombre("Computadora "+i)
							.precio(100 + Math.random()*5000)
							.cantidad(random.nextInt(100))
							.categoria(categoria)
							.build();
					productoRepository.save(producto);
				}
			});
		};
	}
}
