package ca.quickdo.springintro.controller;

import ca.quickdo.springintro.dto.NewProductDto;
import ca.quickdo.springintro.models.Product;
import ca.quickdo.springintro.repository.ProductsRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/inventory")
public class InventoryController {
    private final ProductsRepository productsRepository;

    public InventoryController(ProductsRepository repository) {
        this.productsRepository = repository;
    }

    @GetMapping
    public Iterable<Product> getAllProducts() {
        return productsRepository.findAll();
    }

    @PostMapping(path = "/products")
    public ResponseEntity<?> addProduct(@RequestBody NewProductDto product) {
        if (this.productsRepository.existsByName(product.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("A product already exists with this nama");
        } else {
            var newProduct = Product.builder()
                    .name(product.getName())
                    .build();
            return ResponseEntity.of(Optional.of(productsRepository.save(newProduct)));


        }
    }
}






